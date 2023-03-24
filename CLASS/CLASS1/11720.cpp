#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
#include <cctype>
#include <queue>
#include <deque>
using namespace std;
typedef long long ll;


int main(){
    cin.tie(0); cout.tie(0);
    ios_base::sync_with_stdio(false);
    
    int n;
    string str;
    int result=0;
    
    cin>>n>>str;
    for (int i=0;i<str.size();i++){
        result+=(str[i]-'0');
    }
    cout<<result;
}
