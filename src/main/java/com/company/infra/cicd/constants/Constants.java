package com.company.infra.cicd.constants;

// mostly name and id are same
public class Constants {

    public static final String COMPONENT_NAME = "CdkCicd";
    public static final String COMPONENT_PKG_NAME = COMPONENT_NAME.toLowerCase();

    public static final String LIB_NAME = COMPONENT_NAME + "Lib";
    public static final String SERVICE_NAME = COMPONENT_NAME + "Service";

    public static final String USE_NAME = LIB_NAME;

    public static final String REPO_ID = USE_NAME;
    public static final String REPO_NAME = REPO_ID;

    public static final String PIPELINE_ID = USE_NAME + "Pipeline";
    public static final String PIPELINE_NAME = USE_NAME + "Pipeline";;

    public static final String STACK_ID = USE_NAME + "Stack";

    public static final String DEFAULT_REGION = "ap-south-1";

    public static final String CODE_ARTIFACT_CFN_INCLUDE_ID = USE_NAME + "CodeArtifactCfnInclude";

    public static final String BUILD_ID = USE_NAME + "CodeBuild";
    public static final String BUILD_NAME = USE_NAME + "CodeBuild";

    public static final String BUILD_ROLE_ID = BUILD_ID + "Role";

}
