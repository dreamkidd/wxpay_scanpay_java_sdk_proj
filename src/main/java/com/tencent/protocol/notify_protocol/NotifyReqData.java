package com.tencent.protocol.notify_protocol;

import com.tencent.common.report.XStreamCDATA;

/**
 * description
 *
 * @author Dream_Kidd
 * @since 2016/11/4
 */
public class NotifyReqData {

    @XStreamCDATA
    public String return_code;
    @XStreamCDATA
    public String return_msg;

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

    public NotifyReqData(Boolean flag, String return_msg) {
        if (flag) {
            this.return_code = "SUCCESS";
            this.return_msg = return_msg;
        } else {
            this.return_code = "FAIL";
            this.return_msg = return_msg;
        }
    }

    @Override
    public String toString() {
        String name = this.getClass().getName();
        final StringBuilder sb = new StringBuilder("<").append(name).append(">");
        sb.append("<return_code>").append("<![CDATA[").append(return_code).append("]]</return_code>");
        sb.append("<return_msg>").append("<![CDATA[").append(return_msg).append("]]</return_msg>");
        sb.append("</").append(name).append(">");
        return sb.toString();
    }
}
