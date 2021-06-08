package com.my.finalProject.tags;

import com.my.finalProject.db.entity.entityImpl.Account;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


public class hiTag extends TagSupport {
    private Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int doStartTag() throws JspException {

        try {
            pageContext.getOut()
                    .append("<h1>");
            pageContext.getOut()
                    .println("Hi " + account.getLogin());
            pageContext.getOut()
                    .append("</h1>");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return SKIP_BODY;
    }
}

