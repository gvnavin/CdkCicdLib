package com.company.infra.cicd.pipeline;

import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.services.codebuild.Project;
import software.amazon.awscdk.services.codecommit.Repository;
import software.amazon.awscdk.services.codepipeline.Artifact;
import software.amazon.awscdk.services.codepipeline.actions.CodeBuildAction;
import software.amazon.awscdk.services.codepipeline.actions.CodeCommitSourceAction;
import software.amazon.awscdk.services.codepipeline.actions.ManualApprovalAction;

import java.util.ArrayList;
import java.util.List;

public class Actions {

    @NotNull
    public static CodeCommitSourceAction getCodeCommitSourceAction(Repository repo, Artifact sourceOutput) {
        return CodeCommitSourceAction.Builder.create()
                .actionName("Source_CodeCommit")
                .repository(repo)
                .branch("master")
                .output(sourceOutput)
                .build();
    }

    @NotNull
    public static CodeBuildAction getCodeBuildAction(Project project, Artifact sourceOutput) {

        Artifact buildOutput = new Artifact();

        List<Artifact> buildOutputList = new ArrayList<>();
        buildOutputList.add(buildOutput);

        return CodeBuildAction.Builder.create()
                .actionName("CodeBuild")
                .project(project)
                .input(sourceOutput)
                .outputs(buildOutputList)
                .build();
    }

    @NotNull
    public static ManualApprovalAction getManualApprovalAction() {
        return ManualApprovalAction.Builder.create()
                .actionName("Approve")
                .build();
    }

}
