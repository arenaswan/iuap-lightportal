package com.yonyou.iuap.pap.lp.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.yonyou.iuap.pap.lp.init.ContextProperties;
import com.yonyou.iuap.pap.lp.init.WebConstant;

public class UserYhtCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String userType= ContextProperties.getUserType();
        boolean result= WebConstant.USER_TYPE_YHT.equalsIgnoreCase(userType);
        return result;
    }
}
