import java.util.*;

class KingdomSpeech {
  
     public static String translate(String speech, String[] vocabulary) {
        List<String> speechList = speechSplit(speech);
        List<String> vocabList = new ArrayList(Arrays.asList(vocabulary));

        List<String> replacement = null;
        do {
            replacement = findBestMatch(vocabList, speechList);
            if (replacement != null) {
                speech = speechReplace(replacement.get(0), replacement.get(1), speech);
                vocabList.remove(replacement.get(1));
            }
        } while (replacement != null);

        return speech;
    }

    static List<String> speechSplit(String speech) {
        String[] speechSplit = speech.split("[^a-z*]");
        List<String> speechList = new ArrayList<>(Arrays.asList(speechSplit));
        speechList.removeIf(s -> s.isEmpty());
        return speechList;
    }

    static List<String> findBestMatch(List<String> vocabulary_, List<String> speechWords_) {
        for (String speechWord : speechWords_) {
            List<String> list = new ArrayList<>();
            list.add(speechWord);

            for (String vocabWord : vocabulary_) {
                if (vocabWord.matches(speechWord.replace("*", "."))) {
                    list.add(vocabWord);
                }
            }

            if (list.size() == 2)
                return list;
        }
        return null;
    }

    static String speechReplace(String speechWord_, String keyWord_, String speech_) {
        int iReplacementLen = 0;
        char[] speech = speech_.toCharArray();

        for (int i = 0; i < speech_.length(); i++) {
            char cSpeech = speech_.charAt(i);
            char cSpeechWord = speechWord_.charAt(iReplacementLen);

            if (cSpeech == cSpeechWord) {
                speech[i] = keyWord_.charAt(iReplacementLen);
                iReplacementLen++;
            } else {
                iReplacementLen = 0;
                speech = speech_.toCharArray();
            }

            if (iReplacementLen == speechWord_.length()
                    && i < speech_.length() - 1
                    && !(speech_.charAt(i + 1) >= 'a' && speech_.charAt(i + 1) <= 'z')
            )
                break;


        }
        return new String(speech);
    }

}
