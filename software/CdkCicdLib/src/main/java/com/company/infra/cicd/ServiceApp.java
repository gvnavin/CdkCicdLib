package com.company.infra.cicd;

import com.company.infra.cicd.names.Names;
import com.company.infra.cicd.interfaces.ServiceWaveCreator;
import com.company.infra.cicd.input.CommonInput;
import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class ServiceApp {

    public static void buildApp(ServiceWaveCreator serviceWaveCreator) {
        App app = new App();

        Names names = serviceWaveCreator.getNames();
        String stackId = names.getStackId();

        new ServiceStack(app, stackId, getStackProps(), serviceWaveCreator);

        app.synth();
    }

    @NotNull
    private static StackProps getStackProps() {
        return StackProps.builder()
                .env(getEnvironment())
                .build();
    }

    @NotNull
    private static Environment getEnvironment() {
        return Environment.builder()
                .account(CommonInput.CI_CD_ACCOUNT)
                .region(CommonInput.CI_CD_REGION)
                .build();
    }
}

