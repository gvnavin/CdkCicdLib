package com.company.infra.cicd.Pipeline;

import com.company.infra.cicd.names.Names;
import com.company.infra.cicd.interfaces.ServiceWaveCreator;
import com.company.infra.cicd.waves.WavesCreator;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.pipelines.CodeBuildStep;
import software.amazon.awscdk.pipelines.CodePipeline;
import software.amazon.awscdk.pipelines.CodePipelineSource;
import software.amazon.awscdk.services.codecommit.Repository;

import java.util.Arrays;

import static com.company.infra.cicd.input.CommonInput.BETA_ARTIFACTORY_DOMAIN;
import static com.company.infra.cicd.input.CommonInput.CODE_ARTIFACTORY_ACCOUNT;

public class Pipeline {

    private static final String SYNTH = "Synth";
    private static final String BRANCH = "master";
    private static final String NPM_INSTALL_AWS_CDK = "npm install -g aws-cdk";
    private static final String CDK_SYNTH = "cdk synth";

    private static final String CODE_ARTIFACT_TOKEN_CMD = "export CODEARTIFACT_AUTH_TOKEN=`aws codeartifact get-authorization-token --domain " + BETA_ARTIFACTORY_DOMAIN + " --domain-owner " + CODE_ARTIFACTORY_ACCOUNT + " --query authorizationToken --output text`";

    @NotNull
    public static CodePipeline setupPipeline(Stack stack, Repository repo, ServiceWaveCreator serviceWaveCreator) {

        final CodePipeline pipeline = getPipeline(stack, repo, serviceWaveCreator);
        WavesCreator.addWaves(pipeline, stack, serviceWaveCreator);
        return pipeline;
    }

    @NotNull
    private static CodePipeline getPipeline(Stack stack, Repository repo, ServiceWaveCreator serviceWaveCreator) {

        Names names = serviceWaveCreator.getNames();
        String pipelineId = names.getPipelineId();
        String pipelineName = names.getPipelineName();
        String codeBuildStepName = names.getCodeBuildStepName();

        CodePipelineSource codePipelineSource = CodePipelineSource.codeCommit(repo, BRANCH);
        CodeBuildStep codeBuildStep = getCodeBuildStep(codeBuildStepName, codePipelineSource);

        return CodePipeline.Builder.create(stack, pipelineId)
                .pipelineName(pipelineName)
                .synth(codeBuildStep)
                .dockerEnabledForSynth(true)
                .build();
    }

    @NotNull
    private static CodeBuildStep getCodeBuildStep(String codeBuildStepName, CodePipelineSource input) {
        CodeBuildStep codeBuildStep = CodeBuildStep.Builder.create(SYNTH)
                .projectName(codeBuildStepName)
                .rolePolicyStatements(PipelinePolicyHelper.getPolicyStatements())
                .input(input)
                .installCommands(Arrays.asList(NPM_INSTALL_AWS_CDK))
                .commands(Arrays.asList(CDK_SYNTH))
                .build();
        return codeBuildStep;
    }

}
