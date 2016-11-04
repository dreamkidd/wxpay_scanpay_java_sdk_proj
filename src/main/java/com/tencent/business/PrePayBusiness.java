package com.tencent.business;

import com.tencent.common.Configure;
import com.tencent.common.Log;
import com.tencent.common.Signature;
import com.tencent.common.Util;
import com.tencent.common.report.ReporterFactory;
import com.tencent.common.report.protocol.ReportReqData;
import com.tencent.common.report.service.ReportService;
import com.tencent.protocol.pre_pay_protocol.PrePayReqData;
import com.tencent.protocol.pre_pay_protocol.PrePayResData;
import com.tencent.service.PrePayService;
import org.slf4j.LoggerFactory;

/**
 * description
 *
 * @author Dream_Kidd
 * @since 2016/11/4
 */
public class PrePayBusiness {

    public interface ResultListener {
        //API返回ReturnCode不合法，支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问
        void onFailByReturnCodeError(PrePayResData prePayResData);

        //API返回ReturnCode为FAIL，支付API系统返回失败，请检测Post给API的数据是否规范合法
        void onFailByReturnCodeFail(PrePayResData prePayResData);

        //支付请求API返回的数据签名验证失败，有可能数据被篡改了
        void onFailBySignInvalid(PrePayResData prePayResData);

        //退款查询失败
        void onPrePayFail(PrePayResData prePayResData);

        void onPrePaySuccess(PrePayResData prePayResData);
    }

    //执行结果
    private static String result = "";

    private static Log log = new Log(LoggerFactory.getLogger(DownloadBillBusiness.class));

    private PrePayService prePayService;

    public PrePayBusiness() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        prePayService = new PrePayService();
    }

    public void run(PrePayReqData prePayReqData, ResultListener resultListener) throws Exception {
        String resString;

        long costTimeStart = System.currentTimeMillis();
        log.i("支付API返回的数据如下：");
        resString = prePayService.request(prePayReqData);
        log.i(resString);
        long costTimeEnd = System.currentTimeMillis();
        long totalTimeCost = costTimeEnd - costTimeStart;
        log.i("api请求总耗时：" + totalTimeCost + "ms");

        log.i(resString);

        PrePayResData prePayResData = (PrePayResData) Util.getObjectFromXML(resString, PrePayResData.class);

        //异步发送统计请求
        //*

        ReportReqData reportReqData = new ReportReqData(
                prePayReqData.getDevice_info(),
                Configure.PAY_API,
                (int) (totalTimeCost),//本次请求耗时
                prePayResData.getReturn_code(),
                prePayResData.getReturn_msg(),
                prePayResData.getResult_code(),
                prePayResData.getErr_code(),
                prePayResData.getErr_code_des(),
                prePayReqData.getOut_trade_no(),
                prePayReqData.getSpbill_create_ip()
        );
        long timeAfterReport;
        if (Configure.isUseThreadToDoReport()) {
            ReporterFactory.getReporter(reportReqData).run();
            timeAfterReport = System.currentTimeMillis();
            log.i("pre_pay+report总耗时（异步方式上报）：" + (timeAfterReport - costTimeStart) + "ms");
        } else {
            ReportService.request(reportReqData);
            timeAfterReport = System.currentTimeMillis();
            log.i("pre_pay+report总耗时（同步方式上报）：" + (timeAfterReport - costTimeStart) + "ms");
        }
        if (prePayResData == null || prePayResData.getReturn_code() == null) {
            log.e("【预支付失败】支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
            resultListener.onFailByReturnCodeError(prePayResData);
            return;
        }
        if (prePayResData.getReturn_code().equals("FAIL")) {
            //注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
            log.e("【预支付失败】支付API系统返回失败，请检测Post给API的数据是否规范合法");
            resultListener.onFailByReturnCodeFail(prePayResData);
            return;
        } else {
            log.i("支付API系统成功返回数据");
            if (!Signature.checkIsSignValidFromResponseString(resString)) {
                log.e("【支付失败】支付请求API返回的数据签名验证失败，有可能数据被篡改了");
                resultListener.onFailBySignInvalid(prePayResData);
                return;
            }
            //获取错误码
            String errorCode = prePayResData.getErr_code();
            //获取错误描述
            String errorCodeDes = prePayResData.getErr_code_des();
            if (prePayResData.getResult_code().equals("SUCCESS")) {
                resultListener.onPrePaySuccess(prePayResData);
            } else {
                log.i("业务返回失败");
                log.i("err_code:" + errorCode);
                log.i("err_code_des:" + errorCodeDes);
                resultListener.onPrePayFail(prePayResData);
            }
        }
    }
}
