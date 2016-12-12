package com.tencent.protocol.notify_protocol;

/**
 * description
 *
 * @author Dream_Kidd
 * @since 2016/11/4
 */
public class NotifyResData {

    //协议层
    private String return_code = "";
    private String return_msg = "";

    //协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String sign;
    private String result_code;
    private String err_code;
    private String err_code_des;
    private String openid;
    private String trade_type;
    private String bank_type;
    private String total_fee;
    private String cash_fee;
    private String transaction_id;
    private String out_trade_no;
    private String time_end;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotifyResData{");
        sb.append("return_code='").append(return_code).append('\'');
        sb.append(", return_msg='").append(return_msg).append('\'');
        sb.append(", appid='").append(appid).append('\'');
        sb.append(", mch_id='").append(mch_id).append('\'');
        sb.append(", nonce_str='").append(nonce_str).append('\'');
        sb.append(", sign='").append(sign).append('\'');
        sb.append(", result_code='").append(result_code).append('\'');
        sb.append(", err_code='").append(err_code).append('\'');
        sb.append(", err_code_des='").append(err_code_des).append('\'');
        sb.append(", openid='").append(openid).append('\'');
        sb.append(", trade_type='").append(trade_type).append('\'');
        sb.append(", bank_type='").append(bank_type).append('\'');
        sb.append(", total_fee='").append(total_fee).append('\'');
        sb.append(", cash_fee='").append(cash_fee).append('\'');
        sb.append(", transaction_id='").append(transaction_id).append('\'');
        sb.append(", out_trade_no='").append(out_trade_no).append('\'');
        sb.append(", time_end='").append(time_end).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
