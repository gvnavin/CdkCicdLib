package com.company.infra.cicd;

import com.company.infra.cicd.constants.Constants;
import com.company.infra.cicd.build.BuildProject;
import com.company.infra.cicd.pipeline.LibPipeline;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.cloudformation.include.CfnInclude;
import software.amazon.awscdk.services.codebuild.Project;
import software.amazon.awscdk.services.codecommit.Repository;
import software.amazon.awscdk.services.codepipeline.Pipeline;
import software.constructs.Construct;

public class LibStack extends Stack {

    public static final String CODE_ARTIFACT_CFN_PATH = "src/main/resources/ca-template.yml";

    public LibStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public LibStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        //final CfnInclude cfnInclude = getCfnInclude();
        final Repository repo = getRepo();
        final Project project = BuildProject.getProject(this);
        Pipeline pipeline = LibPipeline.getPipeline(this, repo, project);

    }

    @NotNull
    private CfnInclude getCfnInclude() {
        return CfnInclude.Builder
                .create(this, Constants.CODE_ARTIFACT_CFN_INCLUDE_ID)
                .templateFile(CODE_ARTIFACT_CFN_PATH)
                .build();
    }

    @NotNull
    private Repository getRepo() {
        return Repository.Builder.create(this, Constants.REPO_ID)
                .repositoryName(Constants.REPO_NAME)
                .build();
    }
}
