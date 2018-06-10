package top.cyixlq.store.generators;

import top.cyixlq.cy.wechat.template.AppRegisterTemplate;
import top.cyixlq.cy_annotations.annotations.AppRegisterGenerator;

@AppRegisterGenerator(
        packageName = "top.cyixlq.store",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
