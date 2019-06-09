package com.mcdenny.atesodictionary;


    public class Word {
        private String englishTranslation;
        private String atesoTranslation;
        private String englishSentence;
        private String atesoSentence;
        private int resourceAudio;


        public  Word(String english, String ateso, String englishSentence, String atesoSentence, int resourceAudio ){
            this.englishTranslation = english;
            this.atesoTranslation = ateso;
            this.englishSentence = englishSentence;
            this.atesoSentence = atesoSentence;
            this.resourceAudio =resourceAudio;


        }

        public String getAtesoTranslation() {
            return atesoTranslation;
        }

        public String getEnglishTranslation() {
            return englishTranslation;
        }

        public void setEnglishTranslation(String englishTranslation) {
            this.englishTranslation = englishTranslation;
        }

        public void setAtesoTranslation(String atesoTranslation) {
            this.atesoTranslation = atesoTranslation;
        }
        public String getEnglishSentence() {
            return englishSentence;
        }

        public void setEnglishSentence(String englishSentence) {
            this.englishSentence = englishSentence;
        }

        public String getAtesoSentence() {
            return atesoSentence;
        }

        public void setAtesoSentence(String atesoSentence) {
            this.atesoSentence = atesoSentence;
        }

        public int getResourceAudio() {
            return resourceAudio;
        }

        public void setResourceAudio(int resourceAudio) {
            this.resourceAudio = resourceAudio;
        }
    }
