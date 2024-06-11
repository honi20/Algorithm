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
    
    string input1, input2;
    vector<int> num1, num2;
    ll result = 0;
    
    cin>>input1>>input2;
    
    for (int idx = 0; idx < input1.size(); idx++) {
        num1.push_back(input1[idx] - '0');
    }
    for (int idx = 0; idx < input2.size(); idx++) {
        num2.push_back(input2[idx] - '0');
    }
    
    for (int idx1 = 0; idx1 < num1.size(); idx1++) {
        for (int idx2 = 0; idx2 < num2.size(); idx2++) {
            result += (num1[idx1] * num2[idx2]);
        }
    }

    cout<<result;
}