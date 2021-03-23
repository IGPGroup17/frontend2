package com.example.personalprofile.repositories;

public class RepositoryConstants {

    // please dont spam this to charge me tonnes thanks :)
    public static final String BEANSTALK_URL = "http://studentpals-env.eba-sgadpib7.eu-west-1.elasticbeanstalk.com/v1/";

    public static final String ELASITCSEARCH_URL = "https://search-event-service-fyrtpugn2ktogtbm47a7vugeu4.eu-west-1.es.amazonaws.com/event-index/_search";

    // endpoints
    public static final String EVENTS_ENDPOINT = BEANSTALK_URL + "events/";

    public static final String STUDENT_ENDPOINT = BEANSTALK_URL + "students/";
}
