package top.cyixlq.store.generators;

import top.cyixlq.cy.wechat.template.WXEntryTemplate;
import top.cyixlq.cy_annotations.annotations.EntryGenerator;

@EntryGenerator(
    packageName = "top.cyixlq.store",
    entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
