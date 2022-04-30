package com.company.infra.cicd.waves;

import com.company.infra.cicd.interfaces.ServiceWaveCreator;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.pipelines.CodePipeline;

import static com.company.infra.cicd.waves.Wave1Creator.addDefaultWave1;
import static com.company.infra.cicd.waves.Wave2Creator.addDefaultWave2;

public class WavesCreator {

    public static void addWaves(final CodePipeline pipeline, Stack stack, ServiceWaveCreator serviceWaveCreator) {
        addDefaultWave1(pipeline, stack, serviceWaveCreator);
        addDefaultWave2(pipeline, stack, serviceWaveCreator);
        //add other waves
    }

}
