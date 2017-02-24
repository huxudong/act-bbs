package com.act.bbs.common;

import act.app.ActionContext;
import act.view.ActionViewVarDef;
import act.view.ImplicitVariableProvider;
import act.view.MailerViewVarDef;
import org.osgl.util.C;

import java.util.List;
import java.util.Locale;

/**
 * Created by huxudong on 17/2/9.
 */
public class TemplateConfig extends ImplicitVariableProvider {
    @Override
    public List<ActionViewVarDef> implicitActionViewVariables() {
        return actionViewVarDefs;
    }

    @Override
    public List<MailerViewVarDef> implicitMailerViewVariables() {
        return null;
    }


    private List<ActionViewVarDef> actionViewVarDefs = C.listOf(

            new ActionViewVarDef("_lang", String.class) {
                @Override
                public Object eval(ActionContext context) {
                    Locale locale = context.locale();
                    locale = Locale.getDefault();
                    return locale.toString().replace('_', '-');
                }
            },
            new ActionViewVarDef("_test", String.class) {
                @Override
                public Object eval(ActionContext context) {
                    return "test";
                }
            }
    );
}
