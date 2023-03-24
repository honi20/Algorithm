#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
using namespace std;
typedef long long ll;


int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    //'A':65  'a':97
    int cnt[26]={0,};
    string str;
    char result='?';
    int max=-1;
    
    cin>>str;
    for (int i=0;i<str.size();i++){
        if (str[i]>=97)
            cnt[str[i]-'a']++;
        else
            cnt[str[i]-'A']++;
    }
    for (int i=0;i<26;i++){
        if (max==cnt[i]){
            result='?';
        }
        else if (max<cnt[i]){
            max=cnt[i];
            result=i+'A';
        }
    }
    cout<<result;
}
