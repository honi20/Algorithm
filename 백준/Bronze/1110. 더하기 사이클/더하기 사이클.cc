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

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int origin, newNum;
    int result = 0;
    
    cin>>origin;
    newNum = origin;
    
    while (true) {
        
        int firstNum = newNum / 10;
        int secondNum = newNum % 10;
        
        newNum = secondNum * 10 + (firstNum + secondNum) % 10;
        ++result;
        
        if (origin == newNum)
            break;
    }
    
    cout<<result;
}
