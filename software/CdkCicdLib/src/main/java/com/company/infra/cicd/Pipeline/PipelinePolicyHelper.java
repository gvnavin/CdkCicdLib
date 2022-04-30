package com.company.infra.cicd.Pipeline;

import software.amazon.awscdk.services.iam.PolicyStatement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.company.infra.cicd.constants.CommonConstants.RESOURCES;

public class PipelinePolicyHelper {

    static List<? extends PolicyStatement> getPolicyStatements() {

        final Map<String, String> stringEquals = new HashMap();
        stringEquals.put(PolicyConstants.STS_AWSSERVICE_NAME, PolicyConstants.CODEARTIFACT_DOMAIN);

        Map condition = new HashMap();
        condition.put(PolicyConstants.STRING_EQUALS, stringEquals);

        PolicyStatement ps1 = PolicyStatement.Builder.create()
                .actions(Arrays.asList(PolicyConstants.GET_STS_BEARER_TOKEN))
                .resources(RESOURCES)
                .conditions(condition)
                .build();

        PolicyStatement ps2 = PolicyStatement.Builder.create()
                .actions(PolicyConstants.CODE_ARTIFACTS_ACTIONS)
                .resources(Arrays.asList(PolicyConstants.CODE_ARTIFACT, PolicyConstants.CODE_ARTIFACT_REPO))
                .build();

        return Arrays.asList(ps1, ps2);
    }
}
