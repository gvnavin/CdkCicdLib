package com.company.infra.cicd.build;

import com.company.infra.cicd.constants.Accounts;
import com.company.infra.cicd.constants.Constants;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.codebuild.*;
import software.amazon.awscdk.services.iam.Effect;
import software.amazon.awscdk.services.iam.PolicyStatement;
import software.amazon.awscdk.services.iam.Role;
import software.amazon.awscdk.services.iam.ServicePrincipal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildProject {

    private static final String ACCOUNT_ID = "Account_Id";
    public static final String CODEBUILD_PRINCIPAL = "codebuild.amazonaws.com";

    @NotNull
    public static Project getProject(final Stack stack) {

        return Project.Builder.create(stack, Constants.BUILD_ID)
                .projectName(Constants.BUILD_NAME)
                .environment(getBuildEnvironment())
                .environmentVariables(getEnvironmentVariables())
                .role(getBuildRole(stack))
                .buildSpec(BuildSpec.fromObject(BuildSpecification.getBuildSpec()))
                .build();

    }

    @NotNull
    private static Role getBuildRole(final Stack stack) {
        final Role buildRole = Role.Builder.create(stack, Constants.BUILD_ROLE_ID)
                .assumedBy(new ServicePrincipal(CODEBUILD_PRINCIPAL))
                .build();
        buildRole.addToPolicy(getPolicyStatement());
        return buildRole;
    }


    @NotNull
    private static BuildEnvironment getBuildEnvironment() {
        return BuildEnvironment.builder()
                .buildImage(LinuxBuildImage.AMAZON_LINUX_2_2)
                .privileged(true)
                .build();
    }

    @NotNull
    private static PolicyStatement getPolicyStatement() {

        List<String> policyResActList = new ArrayList<>();
        policyResActList.add("*");

        return PolicyStatement.Builder.create()
                .effect(Effect.ALLOW)
                .resources(policyResActList)
                .actions(policyResActList)
                .build();
    }

    @NotNull
    private static Map getEnvironmentVariables() {
        Map environmentVariables = new HashMap();
        BuildEnvironmentVariable buildEnvironmentVariable = BuildEnvironmentVariable.builder()
                .value(Accounts.CODE_ARTIFACTORY_ACCOUNT)
                .build();
        environmentVariables.put(ACCOUNT_ID, buildEnvironmentVariable);
        return environmentVariables;
    }

}
