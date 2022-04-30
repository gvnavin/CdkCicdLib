package com.company.infra.cicd.pipeline;

import software.amazon.awscdk.services.codepipeline.IAction;
import software.amazon.awscdk.services.codepipeline.StageProps;
import software.amazon.awscdk.services.codepipeline.actions.CodeBuildAction;
import software.amazon.awscdk.services.codepipeline.actions.CodeCommitSourceAction;

import java.util.ArrayList;
import java.util.List;

public class PipelineStageProps {

    public static List<? extends StageProps> getStagePropsList(
            CodeCommitSourceAction sourceAction,
            CodeBuildAction buildAction) {

        StageProps codeCommitSourceActionStageProps = getCodeCommitSourceActionStageProps(sourceAction);
        StageProps codeBuildActionStageProps = getCodeBuildActionStageProps(buildAction);
        StageProps manualApproval = getManualApprovalActionStageProps();

        List<StageProps> stageList = new ArrayList<>();
        stageList.add(codeCommitSourceActionStageProps);
        stageList.add(codeBuildActionStageProps);
        stageList.add(manualApproval);

        return stageList;

    }

    private static StageProps getCodeCommitSourceActionStageProps(CodeCommitSourceAction sourceAction) {
        List<IAction> sourceActionList = new ArrayList<>();
        sourceActionList.add(sourceAction);

        StageProps stageProps = StageProps.builder()
                .stageName("Source_CodeCommit")
                .actions(sourceActionList)
                .build();

        return stageProps;
    }

    private static StageProps getCodeBuildActionStageProps(CodeBuildAction buildAction) {
        List<IAction> buildActionList = new ArrayList<>();
        buildActionList.add(buildAction);

        StageProps codeBuildActionStageProps = StageProps.builder()
                .stageName("Build_JAR_CodeArtifact")
                .actions(buildActionList)
                .build();

        return codeBuildActionStageProps;
    }

    private static StageProps getManualApprovalActionStageProps() {
        List<IAction> manualApprovalActionList = new ArrayList<>();
        manualApprovalActionList.add(Actions.getManualApprovalAction());

        StageProps manualApproval = StageProps.builder()
                .stageName("Manual_Approval")
                .actions(manualApprovalActionList)
                .build();

        return manualApproval;
    }
}
