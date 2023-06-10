#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321

int result = 40;
vector <int> roundRull[10];

bool check(int Drange, int y, int x){
    int tmp = 0;
    if (0 <= y && y <= 1){
        if ((y == 0 || y == 1) && 6 <= x && x <= 9)
            tmp = 17;
        else if (y == 1 && x == 5)
            tmp = 4;
        else if (y == 1 && x == 10)
            tmp = 1;
    }
    
    else if (0 <= y && y <= 1){
        if ()
            tmp = 7;
        else if ()
            tmp = 4;
        else if ()
            tmp = 12;
        else if ()
            tmp = 1;
        
    }
    else if (2 <= y && y <= 4){
        
    }
    else if (5 <= y && y <= 6){
        
    }
    else if (7 <= y && y <= 8){
        
    }
    else if (9 <= y && y <= 10){
        
    }
    else if (11 <= y && y <= 13){
        
    }
    else if (14 <= y && y <= 15){
        
    }
}

int getScore(int idx, int y, int x){
    int score = 0;
    
    if (idx == 1){
        if (check(5, y, x))
            score += 5;
    }
    else if (idx == 2){
        if (check(6, y, x))
            score += 6;
    }
    else if (idx == 3){
        if (check(7, y, x))
            score += 7;
    }
    else if (idx == 4){
        if (check(8, y, x))
            score += 8;
    }
    else if (idx == 5){
        if (check(17, y, x))
            score += 17;
        
        if (check(18, y, x))
            score += 18;
    }
    else if (idx == 6){
        if (check(9, y, x))
            score += 9;
        
        if (check(10, y, x))
            score += 10;
    }
    else if (idx == 7){
        if (check(11, y, x))
            score += 11;
        
        if (check(12, y, x))
            score += 12;
    }
    else if (idx == 8){
        if (check(19, y, x))
            score += 19;
        
        if (check(20, y, x))
            score += 20;
    }
    else if (idx == 9){
        if (check(25, y, x))
            score += 25;
    }
    
}

int solution(vector<int> param) {
    int roundCnt = param.size()/6;
    
    int tmp = 0;
    for (int i=1; i<=roundCnt; i++){
        
        for (int k=0; k<2; k++){
            int y = param[tmp++];
            int x = param[tmp++];
            
            int score = getScore(i, y, x);
            
            // 조건이 맞지 않는 경우
            if (score == 0){
                if (result == 0 || result == 1)
                    result = 0;
                else
                    result = ceil(result/2);
            }
        }
    }
    cout<<result;

    return 0;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    vector <int> param;
    param.push_back(1);
    param.push_back(1);
    param.push_back(2);
    param.push_back(2);
    param.push_back(3);
    param.push_back(3);
}

