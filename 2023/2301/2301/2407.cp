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

string comb[102][102];

string addNum(string str1, string str2){
    ll tmp1 = str1.size()-1;
    ll tmp2 = str2.size()-1;
    string result = "";
    
    int k = 0, c = 0;    // 일의 자리, 올림수
    
    while (true){
        if (tmp1 < 0 || tmp2 < 0)
            break;
        
        int num = (str1[tmp1] - '0') + (str2[tmp2] - '0') + c;
        k = num % 10;
        c = num / 10;
        result = to_string(k) + result;
        tmp1--; tmp2--;
    }
    
    if (tmp1 >= 0){
        while (true){
            if (tmp1 < 0)
                break;
            
            int num = (str1[tmp1] - '0') + c;
            k = num % 10;
            c = num / 10;
            
            result = to_string(k) + result;
            tmp1--;
        }
    }
    
    else if (tmp2 >= 0){
        while (true){
            if (tmp2 < 0)
                break;
            
            int num = (str2[tmp2] - '0') + c;
            k = num % 10;
            c = num / 10;
            
            result = to_string(k) + result;
            tmp2--;
        }
    }
    
    if (c > 0)
        result = to_string(c) + result;
    return result;
}


string solve(int n, int m){
    if (n == m || m == 0)
        return "1";
    
    if (comb[n][m] != "")
        return comb[n][m];
    
    comb[n][m] = addNum(solve(n-1, m-1), solve(n-1, m));
    return comb[n][m];
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, m;
    cin>>n>>m;
    cout << solve(n, m);
}




/*
 
 5 3
 4 2 / 4 3
 3 2 / 3 1 / 3 3 / 3 2
 2 2 / 2 1
 
 nCm = n-1Cm + n-1Cm-1
 n : 5~100
 m : 5~100
 
 100 6 -> 1192052400
 
 153
 78
 
 
 */



