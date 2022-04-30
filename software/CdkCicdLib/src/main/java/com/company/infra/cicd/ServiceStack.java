package com.company.infra.cicd;

import com.company.infra.cicd.Pipeline.Pipeline;
import com.company.infra.cicd.names.Names;
import com.company.infra.cicd.interfaces.ServiceWaveCreator;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.pipelines.CodePipeline;
import software.amazon.awscdk.services.codecommit.Repository;
import software.constructs.Construct;

public class ServiceStack extends Stack {

    public ServiceStack(final Construct scope, final String id, @NotNull StackProps props, ServiceWaveCreator serviceWaveCreator) {
        super(scope, id, props);

        final Repository repo = getRepo(serviceWaveCreator);
        final CodePipeline pipeline = Pipeline.setupPipeline(this, repo, serviceWaveCreator);

    }

    @NotNull
    private Repository getRepo(ServiceWaveCreator serviceWaveCreator) {

        Names names = serviceWaveCreator.getNames();
        String repoId = names.getRepoId();
        String repoName = names.getRepoName();

        return Repository.Builder.create(this, repoId)
                .repositoryName(repoName)
                .build();
    }

}
