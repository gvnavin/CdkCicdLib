package com.company.infra.cicd;

import com.company.infra.cicd.constants.Accounts;
import com.company.infra.cicd.constants.Constants;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class LibApp {
    public static void main(final String[] args) {
        software.amazon.awscdk.App app = new software.amazon.awscdk.App();

        new LibStack(app, Constants.STACK_ID, StackProps.builder()
                .env(Environment.builder()
                        .account(Accounts.PIPELINE_ACCOUNT)
                        .region(Constants.DEFAULT_REGION)
                        .build())
                .build());

        app.synth();
    }
}

