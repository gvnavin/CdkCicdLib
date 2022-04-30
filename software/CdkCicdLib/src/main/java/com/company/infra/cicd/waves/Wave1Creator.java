package com.company.infra.cicd.waves;

import com.company.infra.cicd.names.Names;
import com.company.infra.cicd.constants.StandardStages;
import com.company.infra.cicd.interfaces.ServiceWaveCreator;
import com.company.infra.cicd.stage.ServiceStage;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.pipelines.CodePipeline;
import software.amazon.awscdk.pipelines.Wave;

public class Wave1Creator {

    public static final String WAVE_1_ID = "Wave1Id";

    static void addDefaultWave1(final CodePipeline pipeline, final Stack stack, ServiceWaveCreator serviceWaveCreator) {
        Names names = serviceWaveCreator.getNames();
        String waveId = names.getUseName() + WAVE_1_ID;

        final Wave wave = pipeline.addWave(waveId);

        ServiceStage stage = ServiceStage.getStage(stack, waveId, StandardStages.STAGE_BETA, serviceWaveCreator);

        System.out.println("stage = " + stage);

        wave.addStage(stage);

//      adding the lambda and table with same name in same region causes cloudformation failure
//      wave.addStage(UserProfileServiceStage.getStage(stack, waveId, Stages.STAGE_PROD, serviceWaveCreator));

    }

}
