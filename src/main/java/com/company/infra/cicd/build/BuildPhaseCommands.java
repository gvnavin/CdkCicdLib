package com.company.infra.cicd.build;

import com.company.infra.cicd.constants.Accounts;
import com.company.infra.cicd.constants.Constants;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildPhaseCommands {

    public static final String BUILD_COMMAND = "./software/" + Constants.USE_NAME + "/gradlew publish -p software/" + Constants.USE_NAME;
    public static final String COMMANDS = "commands";
    public static final String BUILD_CHECK_CMD = "bash -c \"if [ /\"$CODEBUILD_BUILD_SUCCEEDING/\" == /\"0/\" ]; then exit 1; fi\"";
    public static final String BUILD_COMPLETED = "echo Build completed on `date`";
    public static final String PRE_BUILD = "pre_build";
    public static final String BUILD = "build";
    public static final String POST_BUILD = "post_build";

    @NotNull
    public static Map<String, Map<String, List<String>>> getPhases() {
        Map<String, Map<String, List<String>>> phases = new HashMap();
        phases.put(PRE_BUILD, getPreBuildCommands());
        phases.put(BUILD, getBuildCommands());
        phases.put(POST_BUILD, getPostBuildCommands());
        return phases;
    }

    @NotNull
    private static Map<String, List<String>> getPreBuildCommands() {
        List<String> preBuildCommandList = new ArrayList<>();
        preBuildCommandList.add("pip install --upgrade pip");
        preBuildCommandList.add("pip install awscli");
        preBuildCommandList.add("pip install requests");
        preBuildCommandList.add("pip install boto3");
        preBuildCommandList.add("pip install jq");
        preBuildCommandList.add("export CODEARTIFACT_AUTH_TOKEN=`aws codeartifact get-authorization-token --domain beta-com-company-codeartifacts --domain-owner " + Accounts.CODE_ARTIFACTORY_ACCOUNT + " --query authorizationToken --output text`");

        Map<String, List<String>> preBuildCommands = new HashMap<>();
        preBuildCommands.put(COMMANDS, preBuildCommandList);
        return preBuildCommands;
    }

    @NotNull
    private static Map<String, List<String>> getBuildCommands() {
        List<String> buildCommandList = new ArrayList<>();
        buildCommandList.add("pwd");
        buildCommandList.add("ls");
        buildCommandList.add(BUILD_COMMAND);

        Map<String, List<String>> buildCommands = new HashMap<>();
        buildCommands.put(COMMANDS, buildCommandList);
        return buildCommands;
    }

    @NotNull
    private static Map<String, List<String>> getPostBuildCommands() {
        List<String> postBuildCommandList = new ArrayList<>();
        postBuildCommandList.add(BUILD_CHECK_CMD);
        postBuildCommandList.add(BUILD_COMPLETED);

        Map<String, List<String>> postBuildCommands = new HashMap<>();
        postBuildCommands.put(COMMANDS, postBuildCommandList);
        return postBuildCommands;
    }

}
