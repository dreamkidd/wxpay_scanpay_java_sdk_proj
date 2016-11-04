package com.tencent.service;

import com.tencent.common.Configure;
import com.tencent.protocol.pre_pay_protocol.PrePayReqData;

/**
 * description
 *
 * @author Dream_Kidd
 * @since 2016/11/4
 */
public class PrePayService extends BaseService {
    public PrePayService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        super(Configure.PRE_PAY_API);
    }

    public String request(PrePayReqData reqData) throws Exception {
        String s = sendPost(reqData);
        return s;
    }
}
