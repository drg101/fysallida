import { ConfigInterface } from "./Curate";

//treat the filenames like you are in the ROOT of the project.
const config: ConfigInterface = {
    "inputFilenames": [
        "./temp/all_other_data_2005_2009.csv",
        "./temp/gini_index_2005_2009.csv"
    ],
    "outputFile": "./out/out.csv",
    "joinField": "GISJOIN",
    "outputFields": {
        "GISJOIN": {
            "type": "copy",
            "input": "GISJOIN"
        },
        "county_name": {
            "type": "copy",
            "input": "COUNTY"
        },
        "total": {
            "type": "copy",
            "input": "RK9E001"
        },
        "bachelors_degree": {
            "type": "math",
            "input": [
                "RM8E015",
                "@@ADD",
                "RM8E016",
                "@@ADD",
                "RM8E017",
                "@@ADD",
                "RM8E018",
                "@@ADD",
                "RM8E032",
                "@@ADD",
                "RM8E033",
                "@@ADD",
                "RM8E034",
                "@@ADD",
                "RM8E035",
                "@@DIVIDE",
                "RM8E001"
            ]
        },
        "gini_index": {
            "type": "copy",
            "input": "R3FE001"
        },
        "median_household_income": {
            "type": "math",
            "input": [
                "RNHE001",
                "@@TIMES",
                1.32
            ]
        },
        "median_age": {
            "type": "copy",
            "input": "RKZE001"
        },
        "white": {
            "type": "math",
            "input": [
                "RLAE002",
                "@@DIVIDE",
                "RLAE001"
            ]
        },
        "black": {
            "type": "math",
            "input": [
                "RLAE003",
                "@@DIVIDE",
                "RLAE001"
            ]
        },
        "native_american": {
            "type": "math",
            "input": [
                "RLAE004",
                "@@DIVIDE",
                "RLAE001"
            ]
        },
        "asian": {
            "type": "math",
            "input": [
                "RLAE005",
                "@@DIVIDE",
                "RLAE001"
            ]
        },
        "poverty": {
            "type": "math",
            "input": [
                "RNBE002",
                "@@ADD",
                "RNBE003",
                "@@DIVIDE",
                "RNBE001"
            ]
        },
        "owned": {
            "type": "math",
            "input": [
                "RP9E002",
                "@@DIVIDE",
                "RP9E001"
            ]
        },
        "occupied": {
            "type": "math",
            "input": [
                "RP8E002",
                "@@DIVIDE",
                "RP8E001"
            ]
        }
    }
}

export default config;