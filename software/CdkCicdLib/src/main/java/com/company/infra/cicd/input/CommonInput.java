package com.company.infra.cicd.input;

//this is related to repo, build ci/cd setup.
//so, these values will not change for every service

public class CommonInput {

    public static final String COMPANY_NAME = "company";

    public static final String DEFAULT_REGION = "ap-south-1";
    public static final String DEFAULT_ACCOUNT = "123";

    public static final String CI_CD_REGION = DEFAULT_REGION;
    public static final String CI_CD_ACCOUNT = DEFAULT_ACCOUNT;

    public static final String CODE_ARTIFACTORY_ACCOUNT = DEFAULT_ACCOUNT;

    public static final String BETA_ARTIFACTORY_DOMAIN = "beta-com-" + COMPANY_NAME + "-codeartifacts";
    public static final String BETA_ARTIFACTORY_REPOSITORY = "beta-com-" + COMPANY_NAME + "-codeartifacts-repository";

}
