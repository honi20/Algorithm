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
    
    int n;
    string str;
    int num[31] = {0,};
    
    cin>>n;
    for (int i=0; i<n; i++){
        cin>>str;
        for (int j=0; j<str.size(); j++){
            num[str[j]-'A'] += pow(10,(str.size()-j-1));
        }
    }
    
    vector <int> v;
    for (int i=0; i<31; i++){
        if (num[i] != 0){
            v.push_back(num[i]);
        }
    }
    
    sort(v.begin(), v.end(), greater<>());
    int tmp = 9, result = 0;
    for (int i=0; i<v.size(); i++){
        result += (v[i] * tmp);
        tmp--;
    }
    cout<<result;
}

