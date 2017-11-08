# Converts spells from https://github.com/vorpalhex/srd_spells to proper format to import into application

import json
from pprint import pprint

with open("spells.json") as inputFile:
	inputData = json.load(inputFile)

outputData = []

for inputValue in inputData:
	outputValue = {}
	outputValue["mName"] = inputValue["name"]
	outputValue["mRulebook"] = "SRD5"
	outputValue["mPage"] = 1
	outputValue["mCastingTime"] = inputValue["casting_time"]
	outputValue["mRange"] = inputValue["range"]
	#outputValue["mTarget"] = None
	#outputValue["mEffect"] = None
	#outputValue["mArea"] = None
	outputValue["mDuration"] = inputValue["duration"]
	#outputValue["mSavingThrow"] = None
	#outputValue["mSpellResistance"] = None
	outputValue["mDescriptionPlain"] = inputValue["description"].replace("\n", " ").replace("  ", " ")
	outputValue["mDescriptionFormatted"] = inputValue["description"].replace("\n", "<br>")
	if "higher_levels" in inputValue:
		outputValue["mDescriptionPlain"] += " At Higher Levels. " + inputValue["higher_levels"]
		outputValue["mDescriptionFormatted"] += "<br><br><b>At Higher Levels.</b> " + inputValue["higher_levels"]
	#outputValue["mShortDescriptionPlain"] = None
	#outputValue["mShortDescriptionFormatted"] = None
	#outputValue["mFlavourTextPlain"] = None
	#outputValue["mFlavourTextFormatted"] = None
	outputValue["mIsRitual"] = bool(inputValue["ritual"])

	level = inputValue["level"]
	if level == "cantrip":
		level = 0
	else:
		level = int(level)
	outputClasses = []
	for inputCharacterClass in inputValue["classes"]:
		outputClasses.append({
			"mCharacterClass": inputCharacterClass,
			"mLevel": level
		})
	outputValue["mClassesLevels"] = outputClasses

	outputValue["mSchools"] = [{
		"mSchool": inputValue["school"]
	}]

	inputComponents = inputValue["components"]
	outputComponents = []
	if inputComponents["somatic"]:
		outputComponents.append("somatic")
	if inputComponents["verbal"]:
		outputComponents.append("verbal")
	if inputComponents["material"]:
		if "materials_needed" in inputComponents:
			inputMaterialComponents = inputComponents["materials_needed"]
			for inputMaterialComponent in inputMaterialComponents:
				outputComponents.append({
					"name": "material",
					"extra": inputMaterialComponent
				})
		else:
			outputComponents.append("material")
	outputValue["mComponents"] = outputComponents

	#outputValue["mDescriptors"] = None

	outputData.append(outputValue)

with open('spells_converted.json', 'w') as outputFile:
	json.dump(outputData , outputFile)