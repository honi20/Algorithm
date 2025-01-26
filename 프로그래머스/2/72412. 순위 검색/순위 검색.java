import java.util.*;

class Solution {
    static final String[] LANG = {"-", "cpp", "java", "python"};
    static final String[] TYPE = {"-", "backend", "frontend"};
    static final String[] EXPER = {"-", "junior", "senior"};
    static final String[] FOOD = {"-", "chicken", "pizza"};
    static final int SCORE = 4;
    
    static Map<String, List<Integer>> scores;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        initScores(info);
        
        for (int idx = 0; idx < query.length; idx++) {
            answer[idx] = getAnswer(query[idx]);
        }
        
        return answer;
    }
    
    private int getAnswer(String query) {
        String[] arr = query.split(" ");
        String condition = arr[0] + arr[2] + arr[4] + arr[6];
        List<Integer> list = scores.get(condition);
        int score = Integer.parseInt(arr[7]);
        
        if (list.size() == 0 || list.get(list.size()-1) < score) {
            return 0;
        }
        
        return list.size() - binarySearch(list, score);
    }
    
    private void initScores(String[] info) {
        scores = new HashMap<>();
        
        for (int lang = 0; lang < 4; lang++) {
            for (int type = 0; type < 3; type++) {
                for (int exper = 0; exper < 3; exper++) {
                    for (int food = 0; food < 3; food++) {
                        findFitPerson(info, LANG[lang], TYPE[type], EXPER[exper], FOOD[food]);
                    }
                }
            }
        }
    }
    
    private void findFitPerson(String[] info, String lang, String type, String exper, String food) {
        String condition = lang + type + exper + food;
        List<Integer> list = new ArrayList<>();
        
        for (int idx = 0; idx < info.length; idx++) {
            if (isFitPerson(info[idx], lang, type, exper, food)) {
                list.add(Integer.parseInt(info[idx].split(" ")[SCORE]));
            }
        }
        
        Collections.sort(list);
        scores.put(condition, list);
    }
    
    private boolean isFitPerson(String person, String lang, String type, String exper, String food) {
        String[] arr = person.split(" ");
        
        if (!lang.equals("-") && !lang.equals(arr[0])) {
            return false;
        }
        if (!type.equals("-") && !type.equals(arr[1])) {
            return false;
        }
        if (!exper.equals("-") && !exper.equals(arr[2])) {
            return false;
        }
        if (!food.equals("-") && !food.equals(arr[3])) {
            return false;
        }
        
        return true;
    }
    
    private int binarySearch(List<Integer> list, int element) {
        int start = 0;
        int end = list.size()-1;
        
        while (start < end) {
            int mid = (start + end) / 2;
            
            if (list.get(mid) < element) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        
        return end;
    }
}