import { ConfigInterface } from "./Curate";

//treat the filenames like you are in the ROOT of the project.
const config: ConfigInterface = {
    "inputFilenames": [
        "./temp/all_other_data_2015_2019.csv",
        "./temp/gini_index_2015_2019.csv"
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
            "input": "ALUCE001"
        },
        "bachelors_degree": {
            "type": "math",
            "input": [
                "ALWGE022",
                "@@ADD",
                "ALWGE023",
                "@@ADD",
                "ALWGE024",
                "@@ADD",
                "ALWGE025",
                "@@DIVIDE",
                "ALWGE001"
            ]
        },
        "gini_index": {
            "type": "copy",
            "input": "AMEME001"
        },
        "median_household_income": {
            "type": "copy",
            "input": "ALW1E001"
        },
        "median_age": {
            "type": "copy",
            "input": "ALT1M001"
        },
        "white": {
            "type": "math",
            "input": [
                "ALUCE002",
                "@@DIVIDE",
                "ALUCE001"
            ]
        },
        "black": {
            "type": "math",
            "input": [
                "ALUCE003",
                "@@DIVIDE",
                "ALUCE001"
            ]
        },
        "native_american": {
            "type": "math",
            "input": [
                "ALUCE004",
                "@@DIVIDE",
                "ALUCE001"
            ]
        },
        "asian": {
            "type": "math",
            "input": [
                "ALUCE005",
                "@@DIVIDE",
                "ALUCE001"
            ]
        },
        "poverty": {
            "type": "math",
            "input": [
                "ALWVE002",
                "@@ADD",
                "ALWVE003",
                "@@DIVIDE",
                "ALWVE001"
            ]
        },
        "owned": {
            "type": "math",
            "input": [
                "ALZLE002",
                "@@DIVIDE",
                "ALZLE001"
            ]
        },
        "occupied": {
            "type": "math",
            "input": [
                "ALZKE002",
                "@@DIVIDE",
                "ALZKE001"
            ]
        }
    }
}

export default config;