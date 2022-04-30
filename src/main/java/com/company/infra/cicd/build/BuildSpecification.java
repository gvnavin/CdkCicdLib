package com.company.infra.cicd.build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildSpecification {

    public static final String ROOT_GRADLE = "'/root/.gradle/**/*'.";
    public static final String PATHS = "paths";
    public static final String FILES = "files";
    public static final String VERSION = "version";
    public static final String PHASES = "phases";
    public static final String ARTIFACTS = "artifacts";
    public static final String CACHE = "cache";
    public static final String VER_2_0 = "0.2";

    public static Map getBuildSpec() {
        Map buildSpec = new HashMap();
        buildSpec.put(VERSION, VER_2_0);
        buildSpec.put(PHASES, BuildPhaseCommands.getPhases());
        buildSpec.put(ARTIFACTS, getArtifacts());
        buildSpec.put(CACHE, getCache());

        return buildSpec;
    }

    private static Map getCache() {
        List<String> paths = new ArrayList<>();
        paths.add(ROOT_GRADLE);

        Map filesMap = new HashMap();
        filesMap.put(PATHS, paths);
        return filesMap;
    }

    private static Map getArtifacts() {
        List<String> files = new ArrayList<>();
        files.add("*");

        Map filesMap = new HashMap();
        filesMap.put(FILES, files);
        return filesMap;
    }

}
