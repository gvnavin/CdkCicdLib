package com.company.infra.cicd.stage;

import com.company.infra.cicd.constants.StandardStages;
import com.company.infra.cicd.input.Wave1Constants;
import com.company.infra.cicd.waves.Wave1Creator;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StageProps;

public class StageFactory {

    @NotNull
    public static StageProps getStageProps(String waveId, String stage) {
        return StageProps.builder()
                .env(getEnv(waveId, stage))
                .build();
    }

    @NotNull
    private static Environment getEnv(String waveId, String stage) {
        return Environment.builder()
                .account(getAccountNumber(waveId, stage))
                .region(getRegion(waveId, stage))
                .build();
    }

    private static String getAccountNumber(String waveId, String stage) {
        System.out.println("waveId = " + waveId + ", stage = " + stage);
        if (waveId.endsWith(Wave1Creator.WAVE_1_ID)) {
            if (stage.equalsIgnoreCase(StandardStages.STAGE_DEV)) {
                return Wave1Constants.WAVE_1_ACCOUNT_DEV;
            } else if (stage.equalsIgnoreCase(StandardStages.STAGE_BETA)) {
                return Wave1Constants.WAVE_1_ACCOUNT_BETA;
            } else if (stage.equalsIgnoreCase(StandardStages.STAGE_GAMMA)) {
                return Wave1Constants.WAVE_1_ACCOUNT_GAMMA;
            } else if (stage.equalsIgnoreCase(StandardStages.STAGE_PROD)) {
                return Wave1Constants.WAVE_1_ACCOUNT_PROD;
            }
        }
        return "";
    }

    private static String getRegion(String waveId, String stage) {
        if (waveId.endsWith(Wave1Creator.WAVE_1_ID)) {
            return Wave1Constants.WAVE_1_REGION;
        }
        return "";
    }

    public static String getDdbArn(String waveId, String stage) {
        return "arn:aws:dynamodb:" + getRegion(waveId, stage) + ":" + getAccountNumber(waveId, stage) + " :table/*";
    }

}
