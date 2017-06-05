package com.hkm.ezwebview.models;

import com.hkm.ezwebview.Util.In32;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.Serializable;

/**
 * Created by jack.cheung on 21/6/2016.
 */
public class WebContent implements Serializable {
    private static final long serialVersionUID = 3948213449011464695L;
    protected String baseUrl;
    protected String template;
    protected String content;
    protected String historyUrl;

    public WebContent() {
        baseUrl = "";
        template = "";
        content = "";
        historyUrl = "";
    }

    public WebContent(String baseUrl, String template, String content) {
        this.baseUrl = baseUrl;
        this.template = template;
        this.content = content;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public WebContent setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String getHistoryUrl() {
        return historyUrl;
    }

    public String getTemplate() {
        return template;
    }

    public WebContent setTemplate(String template) {
        this.template = template;
        return this;
    }

    public String getContent() {
        return content;
    }

    public WebContent setContent(String content) {
        this.content = content;
        return this;
    }

    public WebContent appendCSS(String cssCode) {
        Document document = Jsoup.parse(template);
        document.head().append(String.format("<style type=\"text/css\">%s</style>", cssCode));
        template = document.outerHtml();
        return this;
    }

    public String getRenderedHtml() {
        if (template == null || template.equals("")) {
            return content;
        }
        return In32.mergeTemplateHtml(template, content);
    }

    public String toString() {
        return getRenderedHtml();
    }
}
