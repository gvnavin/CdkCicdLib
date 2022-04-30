package com.company.infra.cicd.interfaces;

import com.company.infra.cicd.names.Names;
import com.company.infra.cicd.stage.ServiceStage;

public interface ServiceWaveCreator {

    void addLambdaStack(final ServiceStage serviceStage, String waveId, String stageId);

    String getUseName();

    default Names getNames() {
        return new Names(getUseName());
    }
}
