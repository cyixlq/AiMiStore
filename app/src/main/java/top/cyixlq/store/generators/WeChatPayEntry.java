package top.cyixlq.store.generators;

import top.cyixlq.cy.wechat.template.WXPayEntryTemplate;
import top.cyixlq.cy_annotations.annotations.PayEntryGenerator;

@PayEntryGenerator(
        packageName = "top.cyixlq.store",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}