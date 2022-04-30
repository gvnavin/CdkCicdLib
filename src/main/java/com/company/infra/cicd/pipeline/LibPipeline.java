package com.company.infra.cicd.pipeline;

import com.company.infra.cicd.constants.Constants;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.codebuild.Project;
import software.amazon.awscdk.services.codecommit.Repository;
import software.amazon.awscdk.services.codepipeline.Artifact;
import software.amazon.awscdk.services.codepipeline.Pipeline;
import software.amazon.awscdk.services.codepipeline.StageProps;
import software.amazon.awscdk.services.codepipeline.actions.CodeBuildAction;
import software.amazon.awscdk.services.codepipeline.actions.CodeCommitSourceAction;

import java.util.List;

public class LibPipeline {

    @NotNull
    public static Pipeline getPipeline(final Stack stack, final Repository repo, final Project project) {

        Artifact sourceOutput = new Artifact();
        CodeCommitSourceAction sourceAction = Actions.getCodeCommitSourceAction(repo, sourceOutput);
        CodeBuildAction buildAction = Actions.getCodeBuildAction(project, sourceOutput);
        List<? extends StageProps> stagePropsList = PipelineStageProps.getStagePropsList(sourceAction, buildAction);

        return Pipeline.Builder.create(stack, Constants.PIPELINE_ID)
                .stages(stagePropsList)
                .pipelineName(Constants.PIPELINE_NAME)
                .build();
    }

}
