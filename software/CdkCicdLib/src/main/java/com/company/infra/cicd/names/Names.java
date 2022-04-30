package com.company.infra.cicd.names;

public class Names {

    private final String repoId;
    private final String repoName;
    private final String pipelineId;
    private final String pipelineName;
    private final String codeBuildStepName;
    private final String stackId;

    private final String useName;

    public Names(String useName) {
        this.useName = useName;

        repoId = useName;
        repoName = repoId;

        pipelineId = useName + "Pipeline";
        pipelineName = pipelineId;

        codeBuildStepName = useName + "CodeBuild";

        stackId = useName + "Stack";

    }

    public String getRepoId() {
        return repoId;
    }

    public String getRepoName() {
        return repoName;
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public String getCodeBuildStepName() {
        return codeBuildStepName;
    }

    public String getUseName() {
        return useName;
    }

    public String getStackId() {
        return stackId;
    }

}
