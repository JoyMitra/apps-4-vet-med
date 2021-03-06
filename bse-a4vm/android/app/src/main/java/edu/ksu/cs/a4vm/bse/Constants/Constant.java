/**
 * Created by Joy on 2/22/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

package edu.ksu.cs.a4vm.bse.Constants;

import java.util.ArrayList;

public class Constant {
    public static String PREFS_FILE_VET_INFO = "vet_info_file";
    public static String PREFS_FILE_MORPH_INFO = "morphology_settings_file";
    public static String PREFS_GROUP_INFO = "group_info_file";
    public static String PREFS_BULL_INFO = "bull_info_file";
    public static String PREFS_MATING_INFO = "mating_info_file";
    public static String PREFS_PHY_PRAMS_INFO = "phy_params_file";
    public static String PREFS_BULL_MEASUREMENT_INFO = "bull_measurement_file";
    public static String PREFS_BULL_MORPHOLOGY_INFO = "bull_morphology_file";
    public static String PREFS_MORPHOLOGY_COUNT_KEYS = "morphology_count_keys_file";
    public static String PREFS_MOTILITY_INFO = "motility_info_file";
    public static String PREFS_CLASSIFICATION_INFO = "classify_info_file";
    public static String PREFS_COMMENTS_INFO = "comments_info_file";
    public static String PREFS_GRP_MORPH_CONFIG = "group_morphology_settings";
    public static String KEY_VET = "vetinfoKey";
    public static String KEY_MORPHOLOGY = "morphologyKeys";
    public static String KEY_GROUP = "groupKeys";
    public static String KEY_BULL = "bullKeys";
    public static String KEY_MORPHOLOGY_COUNT_KEY = "morphCountKey";

    //id hints
    public static String BULL_INFO_ID_1 = "Tag";
    public static String BULL_INFO_ID_2 = "Tattoo";
    public static String BULL_INFO_ID_3 = "RFID";
    public static String BULL_INFO_ID_4 = "Brand";

    //Bull Info prefix
    public static String BULL_INFO_PREFIX = "Bull ID";

    //Messages
    public static String MESSAGE_TOTAL = "Total Count : ";
    public static String DATE_MSG = "Date Collected : ";
        public static String CSV_MSG = "CSV file Status";
    //CSV headings
    public static String CSV_HEADING = "animal_age," +
            "animal_ageType," +
            "animal_brand," +
            "animal_breed," +
            "animal_dateOfBirth," +
            "animal_lotNumber," +
            "animal_rfid," +
            "animal_tag," +
            "animal_tattoo," +
            "animal_other," +
            "bodyPartInfo_bodyParts_eyes_description," +
            "bodyPartInfo_bodyParts_eyes_normal," +
            "bodyPartInfo_bodyParts_scrotum_description," +
            "bodyPartInfo_bodyParts_scrotum_normal," +
            "bodyPartInfo_bodyParts_feet_description," +
            "bodyPartInfo_bodyParts_feet_normal," +
            "bodyPartInfo_bodyParts_Legs_description," +
            "bodyPartInfo_bodyParts_Legs_normal," +
            "bodyPartInfo_bodyParts_Testicles_description," +
            "bodyPartInfo_bodyParts_Testicles_normal," +
            "bodyPartInfo_bodyParts_AccessorySexGlands_description," +
            "bodyPartInfo_bodyParts_AccessorySexGlands_normal," +
            "bodyPartInfo_bodyParts_Inguinal_description," +
            "bodyPartInfo_bodyParts_Inguinal_normal," +
            "bodyPartInfo_bodyParts_ScrotalShape_description," +
            "bodyPartInfo_bodyParts_ScrotalShape_normal," +
            "bodyPartInfo_bodyParts_epidydimides_description," +
            "bodyPartInfo_bodyParts_epidydimides_normal," +
            "bodyPartInfo_bodyParts_Penis_description," +
            "bodyPartInfo_bodyParts_Penis_normal," +
            "bodyPartInfo_bodyParts_Prepuce_description," +
            "bodyPartInfo_bodyParts_Prepuce_normal," +
            "collectionNumber," +
            "completed," +
            "datePerformed," +
            "generalInfo_classification," +
            "generalInfo_comments," +
            "matingInfo_comments," +
            "matingInfo_performance," +
            "matingInfo_performanceDescription," +
            "matingInfo_seasonsUsed," +
            "matingInfo_sirePastureType," +
            "morphologyInfo_comments," +
            "motilityInfo_comments," +
            "motilityInfo_grossMotilityCategory," +
            "motilityInfo_individualMotility," +
            "motilityInfo_motilityPercent," +
            "physicalInfo_bodyCondition," +
            "physicalInfo_comments," +
            "physicalInfo_frameScore," +
            "physicalInfo_hipHeight," +
            "physicalInfo_hipHeightUnits," +
            "physicalInfo_pelvicXMeasure," +
            "physicalInfo_pelvicYMeasure," +
            "physicalInfo_scrotalCircumference," +
            "rancher_address_address1," +
            "rancher_address_address2," +
            "rancher_address_city," +
            "rancher_address_email," +
            "rancher_address_phone," +
            "rancher_address_state," +
            "rancher_address_zip," +
            "rancher_firstName," +
            "rancher_lastName," +
            "rancher_ranchName," +
            "uuid," +
            "veterinarian_address_address1," +
            "veterinarian_address_address2," +
            "veterinarian_address_city," +
            "veterinarian_address_email," +
            "veterinarian_address_phone," +
            "veterinarian_address_state," +
            "veterinarian_address_zip," +
            "veterinarian_clinicName," +
            "veterinarian_firstName," +
            "veterinarian_lastName," +
            "morphology_count_threshold,";

    public static ArrayList<String> morphHeaders = null;
    public static int sum = 0;
    public static String mfield1 = "Morphology Field 1";
    public static String mfield2 = "Morphology Field 2";
    public static String mfield3 = "Morphology Field 3";
    public static String mfield4 = "Morphology Field 4";
    public static String mfield5 = "Morphology Field 5";
    public static String mfield6 = "Morphology Field 6";
    public static String mfield7 = "Morphology Field 7";
    public static String mfield8 = "Morphology Field 8";
    public static String mfield9 = "Morphology Field 9";
}
