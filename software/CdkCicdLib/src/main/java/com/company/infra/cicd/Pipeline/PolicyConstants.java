package com.company.infra.cicd.Pipeline;

import java.util.Arrays;
import java.util.List;

import static com.company.infra.cicd.input.CommonInput.*;

public class PolicyConstants {
    static final String CODEARTIFACT_DOMAIN = "codeartifact.amazonaws.com";

    static final String CODE_ARTIFACT = "arn:aws:codeartifact:" + CI_CD_REGION + ":" + CODE_ARTIFACTORY_ACCOUNT + ":domain/" + BETA_ARTIFACTORY_DOMAIN;
    static final String CODE_ARTIFACT_REPO = "arn:aws:codeartifact:" + CI_CD_REGION + ":" + CODE_ARTIFACTORY_ACCOUNT + ":repository/" + BETA_ARTIFACTORY_DOMAIN + "/" + BETA_ARTIFACTORY_REPOSITORY;

    static final String GET_STS_BEARER_TOKEN = "sts:GetServiceBearerToken";
    static final String STRING_EQUALS = "StringEquals";

    static final String STS_AWSSERVICE_NAME = "sts:AWSServiceName";

    static final String CODEARTIFACT_GET = "codeartifact:Get*";
    static final String CODEARTIFACT_DESCRIBE = "codeartifact:Describe*";
    static final String CODEARTIFACT_READ = "codeartifact:Read*";
    static final String CODEARTIFACT_LIST = "codeartifact:List*";

    static final List<String> CODE_ARTIFACTS_ACTIONS = Arrays.asList(
            CODEARTIFACT_GET,
            CODEARTIFACT_DESCRIBE,
            CODEARTIFACT_READ,
            CODEARTIFACT_LIST
    );
}
