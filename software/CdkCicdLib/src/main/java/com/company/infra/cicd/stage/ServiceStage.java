package com.company.infra.cicd.stage;

import com.company.infra.cicd.interfaces.ServiceWaveCreator;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.Stage;
import software.amazon.awscdk.StageProps;
import software.constructs.Construct;

public class ServiceStage extends Stage {

    public ServiceStage(final Construct scope, final String waveId, final String stageId, final StageProps props, ServiceWaveCreator serviceWaveCreator) {
        super(scope, stageId, props);

        System.out.println("ServiceStage.ServiceStage addLambdaStack");
        serviceWaveCreator.addLambdaStack(this, waveId, stageId);
    }

    @NotNull
    public static ServiceStage getStage(Stack stack,
                                        String waveId,
                                        String stageId,
                                        ServiceWaveCreator serviceWaveCreator) {

        StageProps stageProps = StageFactory.getStageProps(waveId, stageId);
        System.out.println("stageProps = " + stageProps);
        return new ServiceStage(stack, waveId, stageId, stageProps, serviceWaveCreator);
    }

}