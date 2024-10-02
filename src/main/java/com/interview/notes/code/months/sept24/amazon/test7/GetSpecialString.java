package com.interview.notes.code.months.sept24.amazon.test7;

/*
WORKING

Developers at Amazon are working on a text generation utility for one of their new products.
Currently, the utility generates only special strings. A string is special if there are no matching adjacent characters. Given a string s of length n, generate a special string of length n that is lexicographically greater than s. If multiple such special strings are possible, then return the lexicographically smallest string among them.
Notes:
• Special String: A string is special if there are no two adjacent characters that are the same.
• Lexicographical Order: This is a generalization of the way words are alphabetically ordered in dictionaries. For example, "abc" is lexicographically smaller than "abd" because 'c' comes before 'd' in the alphabet.
A string a is lexicographically smaller than a string b if and only if one of the following holds:
• a is a prefix of b, but a is not equal to b. For example, "abc" is smaller than "abcd".
• In the first position where a and b differ, the character in a comes before the character in b in the alphabet. For example, "abc" is smaller than "abd" because 'c' comes before 'd'.
Important Considerations:
• If the character is 'z', it is the last character in the alphabet and cannot be increased further. The string should not wrap around to 'a' after 'z'.
• The output string must not have any adjacent characters that are the same.
Example
Suppose s= "abbd".
Some of the special strings that are lexicographically greater than s are shown.
a
b
d
a

a
b
C
d
a
b
b
d

a
b
C
a
a
b
C
b
65


The lexicographically smallest special string that is greater than "abd" is "abca". er Rank Ce
Function Description
Complete the function getSpecia/String in the editor below.

getSpecialString has the following parameter:
s: the input string
 nfide
Returns
"-1".
string: the lexicographically smallest string that is greater than s. If no such special string exists, return
Constraints:
• 1 <= | s | <= 10 power 6
• s consists of lowercase English letters only.
 terRank
• Input Format For Custom Testing
The only line of input contains a string s.
• Sample Case 0
Sample Input For Custom Testing
STDIN
FUNCTION
abccde
s = "abccde"
 Sample Output
abcdab
Explanation
Some of the special strings that are lexicographically greater than s are "abcdde", "abcdab", "abcdb"
• Sample Case 1
Sample Input For Custom Testing
STDIN
zzab
FUNCTION
- - -----
s = "zzab"
Sample Output
k CC
-1



Explanation

There is no special string of length 4 that is lexicographically greater than s.
 */
public class GetSpecialString {

    /*
     * Complete the 'getSpecialString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String getSpecialString(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        // Precompute whether the prefix up to each position is special
        boolean[] isSpecialUpTo = new boolean[n];
        isSpecialUpTo[0] = true;
        for (int i = 1; i < n; i++) {
            isSpecialUpTo[i] = isSpecialUpTo[i - 1] && (arr[i] != arr[i - 1]);
        }

        // Iterate from the end of the string to the beginning
        for (int i = n - 1; i >= 0; i--) {
            // Only proceed if the prefix up to i-1 is special
            if (i > 0 && !isSpecialUpTo[i - 1]) {
                continue;
            }

            // Try to increment the current character
            for (char c = (char) (arr[i] + 1); c <= 'z'; c++) {
                if (i > 0 && c == arr[i - 1]) {
                    continue; // Skip if it causes adjacent duplicates
                }
                arr[i] = c;

                // Attempt to build the rest of the string with the smallest possible characters
                if (buildRemainingString(arr, i + 1)) {
                    String newStr = new String(arr);
                    if (newStr.compareTo(s) > 0) {
                        return newStr;
                    }
                }
            }
            // Reset arr[i] to original character before moving to previous position
            arr[i] = s.charAt(i);
        }
        return "-1";
    }

    // Helper method to build the remaining string from position 'index'
    private static boolean buildRemainingString(char[] arr, int index) {
        int n = arr.length;
        for (int i = index; i < n; i++) {
            boolean found = false;
            // Try to assign the smallest character that doesn't cause duplicates
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != arr[i - 1]) {
                    arr[i] = c;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false; // Cannot find a valid character
            }
        }
        return true;
    }
    public static void main(String[] args) {
        // Test cases
        System.out.println(getSpecialString("abcd")); // Output: abce
        System.out.println(getSpecialString("fghbbadcba")); // Output: fghbcababa
        System.out.println(getSpecialString("aabbccdd"));  // Output: abababab
        System.out.println(getSpecialString("zabcdzy"));  // Output: zabceab
        System.out.println(getSpecialString("zzaab"));  // Output: -1
        System.out.println(getSpecialString("abccde"));  // Output: abcdab

        System.out.println(getSpecialString("vbyetnoababababababababababababababababababababababababababcabababababababababababababcababababababa"));
        System.out.println(getSpecialString("vbyetnnzjgqihoyzodkisanxbapavajjzlzocftmwnwvmugrmgsmmdhuyvxcacghlfwcxgeskzcjeywbwpkxorpauuwriigmsobq"));  // Output: abcdab

        System.out.println(getSpecialString("yuplihfedavsqomlhdbywvqpkihdyurmhcxsnkhcyxvspnjgezwrpkfdyutsnlgbzxvsnmhdyutplkgbxtplkhgcxwtsplhdcyxtsqmhdcxvtqpkhdcytqpmhezyvqnjezyusromhedcywsrpnieczwuqplidcxtsomkfbyusomjhedcywsomhcyxsqmhfczwupomlgfavrmlihdczwtokhfawsrpojihedyxurnjgfaxvqmkhfdbwvutokhebwvrqomkfdbzxvqnifazywtonihfcxupmlkhfdcyxsokidzwvronmjgbaxwuqnidzxsqpkidaywsojgecxtspkjfcxwrqnlgfawrmifczvqmlgedyxtqlkhfawsokhfcyupnidczyvqljfcbzusqpnihfebawrmiebytqojgcxwvqnkfdzwusnljidczwutqmjfdazwrokidytpnkfcbawtsokjhgfdyxwsrnkjedbxtpnjhgfdzxtrqnljidyvtomlkiedcavutsnjhdazwtpojhcxwsqojfeayutojhdavrmlkgcxvqnkgfaytsnlihdawsnihgdazyvrqomlgcxuplkjgfbwuspkheaxupmieavsqmjfcytokidawupljeawsojeczwsrpmihgedzurmigfcytqomhdyvsrmkjgecawsrnigcbxspmifdyvqpmkfecywusrolhfavsqlhfaxupnkjhgbyxvsnmhfcyvusnlhfedyxuqlgebxtqoljhfcxuqpnmhcbywrniecxtonkgbzvrolihcywurojhczupojgbyxutrpnkifbwtrqljhdazyvupkjfcxsokgcytqljigbyxspnkjifdcxsokfecavtoljfczytpokjifeawrqmlhgeczwrplkgdbzuromheayvsqpomhfebyxsomljfeayvrplgcbwtspmjgczvsomljedbzxsromjeavuplhczwutpnidbzwtrnigedbavurpnjhezvrojhdzxurmkfecyuqmlidaxsrmhfcytsomifcbxuspmkigbwtsnljhdazvsolheazwtqomkfdcbxuqnihdyxupnjfcyusplgcxusnkiedytqojeazyxtqljebzwvrojecyvupomjezvqmifeaxvuqpmlkhebzvrnkgfdzvqlgcywtqolhcayxtplkgbyvuqmhfczxvqljhezxtpnjgezwvtqnidczxtqljgcyusokieczxwsrpokgdyutojhdzywsokjgedzvurolgcxsqpkfdbyvrmigcxsnlgecaxsonjeazusrmhezwtpnidazvtplgbywvrqmjgbzupmjihgbwvqlidawrokhgeaywromhezvurolgbyxsplgfdytsrpokhgfaywtsolkfdbxvrpmifezwusolhczywtpokhdytpkfbxtqlkigcyxtqlhgfdcbaxvtsnmjgfezwvsrmhcxvsqmifedzwsqlkfbytojgdcxwsqnmhfcbxutojgebzywrqokiecytojhdzwtpmkgcywsqomhdzvrmigdbywvtqmifayupkgcaxsrolkfcywusrqpnljihfbayvqlhgdbxsolgcbwusomjgfdavqljhcytomkfczurmjheavrpokjfedywtsonihdzytrnidyxsnlhdcbxsqonkfcxtsrolihfdbytrokhdazyxwromjhgbavrmjgdawsrnjieaywupojfcxuqpokhgfezxutpnmifbayutsrqmlgfcaytrpkiecbxtpnlkjgdzyxtpnidzxvsqokiezvrpkfdywvtolgbytqplhfbyxusokgdzvtrpljfbxvtsnjifavsojfbxtojgbaxtsqmhcbywrpmhebawtonjfazxsokfbyutomkgecxutrqomhfdytpmidbayxuqligfdzvqpnjifebywtpkhcxsnlhdzvuspliebaxuponidzyupomidbytpnjifeayupnjedzyvtpnlgdaxtpnmlkfcxsniecbwvqplkiebayxsnkigedzxwsrnigfeayxsrnlheavsqnkhcazwrqlihdavtrmljedcyxvqljfbawvtonkfcyxsqlidzxsonidbaytqmkjfeaxvupmkgcxurqliezvtrqlihcavutrqpkgbazuqmjfcayxvsonmlgdytplieavrmkhdcyurokfbxvtqnkhczvrmjfawvrpokfawuqlkihdyuqomkhgdzxwusqniedbxspmhebavupkhdbazvsqnkgecaxsnjidzxwvrojihgebazwtqmjgfcaytponkifedzxwtplkjiedzusrnmhcxvtojgebwtonkfdbxtrmkgdzxsqokgfeavutonljiezxsnlkjebazutqmjhgfazupljfeaxwrpnlgbxsnkigcywusnlgezyuplihebaxurokhfdcbytsrokigfcytonifavqplkfcayxsqlkigdyuqpkhezvuqmjezwuqmhfbzupnihedyvqpnjeaxtqoljfavqojecbwrolhfdzwutrmkfazyvtsolgbaxwronjecaxtrpkfecayxvtpkfbwsrojhgfebyusrpkhdcaytrmlhdyvsnljfecbxvtrqpnkgbxvsnidavtsrqmidzuqoljihczywuspniebayutokhecxvurqlkhgfedbzurqpmkidytrmidyvsojhdyvrojfbxutqlhdcbavrpnlhcbavqmigfbyxwusomifazxvqoljhcxvqnkiecavqlihcxupomjecbxvurmkjhdcxvqnlgbwtqolgdywtqnigcxwsoljfawtrmlgcxuqmkigcayvspnkjgcxvtolkjebyxsrplhcytqpmhczywvsrolgfbavutqpnlgedyxupkieazxtqlkgbxvupkhedaxsnidbzusnjecavsojgeawsnihezwrnkgfcytsnlgfdyxwtspljeazxvsnkfebwtpmhedytqnjhgcaxsronmjhdaxtrqpmihdbyvsqokhfbzvtrpomjebzwspokfcxsrqnjigbzvtponkfdzxtrqlkgbwvtsomigebwtqnigdyxwtsrnjhgecbwvtpolifaxtqlidzvuqolheayusojihdbwsojgfbxsqpnmlhdaxvqojihgdzxurniebyxtqplkfczxsrqnkgcbwrmlkgcxvutojezwsnjecbaxtpnihdbxwtrplkgfbazytqonljedawtpnjhgdczurmkjhfazurmigecyxtrnlhcytsrmjieczxtrpolkjihdyxtrnmlgcxtsqpkhfawvrqmkifcyvsonigfazytrpliebaxsnkhcywtqlkjhecxsrnihedavrpmidytpnkfdzvsonifdbzvqpmjfedazurnjidczwtrmjhedczuromkgfdbawsrpkhcazurqlgebazwvusrmhdytrokifdbxvspnjhdczwvqomjgedaxtsomhczxtspolgczuqpmlgezuqpliedywvrqnmjeavrpmkhgdcbzxvrpomkiezvutokfeaywtromkjfdazusrplkiecxvqnidzwsqojfbxtqmieawtqmkjifaxsqoligfaytonigdcayxurmkhdczxvqnlhcbazvsrplkihdbawvspnidywvqnliecbzywusplihgecywsronjfdaywvqonjhfcbazvsrmigfczupojgbzyvqmkgfdbyxwtrqlkhczwtonmljhdbxtqlgfawtqomjiecyxtomjihezyurnmhfbywrqnkhgbwtsqonjgfbxsojfcbxvsqnkfcxvqonlgbxusqmlkhfaxwvutrmlkgcywvroljigbaxupkjebytpkhezwvqojidzvsnlgezxtqpmidywvrqpokgcxupnigcavqonlkfaxwtrmlgdzvtqljfcyvsqpkgcbzuromlkjgbzvutomhczwutrmhfaxwvsponkfcyvrojfczyvqponmhezvtpokjfdzxusronmkhfedywvsnmlhgfdywrqomkihgdbaxtokhecbwusnifaxwvtpmkigecxwuqokjedzyxsqmhezvtojgbaxwspkifecbywtpmhcawtsonmkfedcyuqmhcbxtrnkfdbzuspkfcbavusrolhcayutronkhgfbxtqnjecxtpnmljebwrqolhezwupkjidzusqokjfcazvqlkgdytronmjgdyuqlgfcywtokigcxvrnmlieavuqpoljfczytqlhfavuspnlhfawvqmifezupnjfdazyupljezuronieazwvurqlhdyvtsqonjidaxvsromkidbawrplgfbavqoljgbwtolgfbayxwvrokfdawrojihdbwurokhezyxtsomihedbavqmjedyupkfeaxvrqmhfazwsokfeawvupmigczyvsrojhgcxsqlidcbzwvrolhdaywsqomhdyvtomlifecywvqpmhecaytojhfczxtrojfczxtrokgczyxspkfbwusnlkigcbxusomlhcyvsrnidytrpmkfbyutqpkjebytsqlihcyvtojihgdcaxutpmjgdzwsnihczyvurqoljgdyxvqlkifazwsojhgbawsnifcbazxvusrmjhdzyupljhdcayvtqomhgeazutonjieaytqnjhdczxwrqomlhebaxtolkgcxvsnkihebazxvtpkjiebyvspnidzwuqmkheaxsokihcytoljfbwvrmhcyxsrmhfdzyupkiezwsniebwsnkfeavuqpkgcxvuqokgeaxurmihcxwsqmihczywtrqmjidbxtrnkjedayvuqplifcyvqpmkjezyxsojgebytponkfaywrmieaxurmidcxvrmkgbaxvutplhfeawuqlhdczyupnjifavrqniebazxsqlhgdczxupmkihdbaxsoljfbyxtplhczyutqpnkieczvrpmhecbavrnlhcyutqlkhfayvupkjgfbzvqnljfbxurmlifdaytrolhcyvrpmjfezyuspkfaxvtoligbyvtsrnjigbwtspmkgebaxtqmjidzvrpljhgeavqmlkjgdyutolkjgebazvrnmhgcbaxtqnkidytpkfbxtomkihgezyutolhgbxuqmlgfdyxutokfbwsokfbxsqnlkfebaxsrplkgdbyvrnlhfaxsojgeayvtolidcxspojhdaywtponkjhfdzxsoljebwtrplkhgedazwuqojfawrojiheazvuqmkhfaxuqnidbwvqmigfbazupmkjebzxwsolkgczvqpmjhedzxwvuqokhdavqmhgbzxurmheczvrqolgdyupkifdczwusqomlgdyvsnidyvqnkigbytojigezupokgfbyvqmliebwsnkfayupkfavrnjgfdzwrnjecxupkjeawupkfeazvusqmhdcytpkhczwvqmkjhgdcxwurnjigbyuqnihfavupkheaytqpnkfecxtqmhedyusrmjedcyxsnjgeavrplhczxtrmligcayxtrqlhgdbyxusnkgcxwvrqligdywvsomifbawvronigczvutrnkjhcyxvromhdcbzupmiedcbaytomhdcbzvronlgcbzxtplgbyxtomlidyvqponidawtpnjebyurpolkigfbwsqnjfcbazxsrqmkhfcawurpmkjecxtqmifazytponjigeazxusplgfawvronjidzxsojfcxupojebavsnifdyvspomidyupnmjebzwupnlhcaywvspligfaytsolkgdyxwsrnkjhcbxtpmigbxwupkihdcxwuqlgbwsqolkiecazvtsrpkhfawtsonkgbazuqnljidzvtsnjhgfbavupkfczwsokjfdczxsplkjhgcytsrqnmjfdywvtpmhcxvqojezuqnlkfbzutrnigdcxuspkjfbzvrpnlhgcytqomihgebxvrqnifcbywtonlkjigbxtqonigbzwvrmihdzwrpojifdcyuqpnmhgfeayuqnmkhdyupokiezxutsrqlidzvrnkgdyxvsonifcbwrpljeaywrqnihfcaxvsnjeczwsqmjhfeawusqnjfcbzxsokhdazxvtpmjihdavqnmigcawtokidzytqnlgbzytqlkjfczvqlgdzwrqlgcyvsnlgdaxuroljiecywtqonjedawsrnjiecxvqmiezxsplgcxwrmhfczvutplhgcbxvusnidbyxusonkfeawutonjfeavrnkgdywsojecbzyvsrpkfdczvsnjhgeawusrnlhgbyuqmjhdyvtplkfebyvupojgfedaxusokgczxvromieavqnifdcavqlhgezwuqpnlidzywupmhczwvurqnkidcaywrqlgcxutpnkfcaxvtsrqmigfbyurpmkhgbxurplhgdbxwtrokgecxwvqmkgdcavrmkgecxupokigfebxwurqolhgbwtspmlhdzvsonjihdbwsnmidcaxtojifawtrnkjgbzuspolhcbwvrolifedbaxupkfbawrokfebaxsniecaxtpnljgfdbawsnjfebzxspljhcyxwvqmkfdzvurqnjecxvqljhfayvqpkgbyxwusqnigbxsokhgezyxwrnkfdayxurmkgbayutplkjgdzuqnjgebxsnmjgezyuqmifeazwvqmkhedcyuspnjfebawsojhdzxvtqnkheaxwtsqlkgeaywuspnjfezxsniebxwvrmkfbzuqmjfbzxspnmigcxwvqonlhcbwvqpmiedcyxsonmjfdzwsqnljhdcbyxurqmkfawrnkjiedcbyusolhczyvsnjgbawvurnjhcbaxvrqpkifezwvrnjgczvtrqpnkfbawusplkfayuqmlhebxutplkigbwsojeawupniebazxvsnjgdzywuponlkhgfcxutqnjhdytojfawvqomljhdaywrmkjgbywronlkgcxsqpkidayxtrqnligezxtrojhfdcbytspkfbywuponmljfazwrqonifezuqokidbxvqmkhdzutonlkhdyuromhdyxtpligdcbzuplgfawsomlgcxtsqokidzvqomhedczurnmidbaxtrojgeaytolidywsokgbaxvqponidayuqnmkhczwtojfbawrmhdywtsqnmjeawrponieazyuqpoljhfbxwsnkgebaxvsnidyvsqoniezurqmjgfecxvsnkhecbzvtspomligbytplkgebyvrmhgeaxvusrqpnmkhecxuqmkgcbzuqljidyxurnkhdavqonlgcayvusqpmjgbayxtqmkhgbavupomlgcawtqnjhgedbwsnjihfavutonigbwsrpnkjfcyuspomiedzvutrqnlhezvtrokfazuqmjihczyvroljhfaxwvsrpnifawsrnjiezurmihcxwuqpmkieayxsplhgbwutsnjedbwtsnljhfazxsnlhdaxsokfdbzxtoljezxuplgecxtqlkhcbyxtrmkfdavrnmlkjgdzwtqplgbxvqpmieaxsokfecbazyusnidcaxtrqlhfedayxtrqojifcaywtsqmidzwrmhfcbaytomjfcavtpnmihdzusnjgcbyvqmhezywsnjfdzutokfebxwuqpmhfdzxutomihfcywvqmjhfbavtsrmigezyxvuqpkgfbytoniebzvtpkjihgeawutomjfedcyxsrpkihebwurpliedazyxtrmiezxupnkjeazwtrokjfedytpoliedytokhebxwrnjhgbwrpomjfebawvqpmjgbxwvtqlhczusrnigfdayvsrqpokjhgcaytqpomkidzvurmjfdzusrpkheczyutplhfedyuqnljhgcxsrmhcbawvsojidcbxwvtomifbavtqomjhgdyvrplhebxvrnmiedaxwutrnkhcxsojfawvrqokgbytokfdywsqnigebytsomifezuromlihecbavqokjeaxtpmjgdbzwvqnkfawutqnkgeaxspolgcywtromihcxsnjifaxsnmigebytspmjeaxwtrnmljhcawvqomhfdzvuqonieawusrmkiezutqlgfeczxwupojigedzwsnjihcxspljgbaywusqpojedaytsomjgdzuqmjecayusolgdaxwuqmkifczusqnkidczutqmligedawtrnjgdzwsqnmiecyvqponifebaxwutrpmhfdcaywrokfdcbyvurnigeawvupljfbxwrokjihgdbawrnmkgfedzxwrnifdbawvusonidyvrnjgcyxuqoljihdywrnkjhcbyvtpmhdzvrpnlhgbxtpmjidzxsqpmhcazwutojfcywtrnjfcyusrmjgbwtqpnjihdbzwvtrplhdzvtrmigfcawrqplkfbwronkihdywvrnjgdcxwvqpomidbwsrplkgdzwrmigdbayxsqmkgbxvqojhgbwsrokjigecxwrpkjgbwsroljhdaxsnjezywsrqpmjfdawronlidaytsojfcbxwvsqpmjecavqpokjiebawuplihcayusponihfezutrpnjfcawsomkgdazurqnjfcavuplgfdywvtonifavsrmhfcazvqpmkigfaxwtpokjeavspkigecyurolkjifawvqpnmlgcyurqlhfezytronlgebxuplgdbxwupomlhcbaxurnmkjhdawuqmhfdyxuqpokhedywsnlgbyvsronjhdcazutspmigedczywsqmlheaywrnifbyuqmihebwrnmjfcbxvqligbzwtonkjfdyvqnljgbyxutpnmjfdyusnkihcxuqonkjgcxwrpokigbawvsomkfawtonieczvqnidbazyxwvqpniecxvutplhfdczxwutojigcyutrqnlkfazwvqmhgdcxtqokgdazwuqomhfcytrpmjihgfcywrmidbzywurmigedaxvusqonlkhcxurpmljgbavupkidyxtrnjiedczywrqonjebwtpmjihdyupojgcytrmkgdyxvqmjhfcbzuqmihgdayupmhcxvrojebxwuqonkigcytrojfdaxvqnidbxsnjgcxutokfebxutqpkfczxsnlhedyvtpomifbxuqpolkjgdbywspnlkihdyxwtqpkjifdzxtsomjfcaxsronkgeazxtonihdbwrnjiecytqokhdbwrnkhgczxurojidawrokjecbxtoljfazxsnigeaytolkifebzxsrqlgdzxtpligcazxspmkjebyvtpnjecyuqljedcxspmkjhcaxwrqpkgbavuplihdawrolkfeaxsnjgcbwrmjfbytsqlkjedczyurmhfbwrqnlkjifczwrqnkfcywurmlidyuplgfdayuqmheazvqmjfdyupmlifawrqokjfczupkfcawvutonkgdbwvtomjgcxwvqnlhfaxsqmidavromhfebwsplgezxwsolgbzvqmhfdcbxtqmkidyvsrnkiezvtolkhecyxvqomkhcxupnkgdawurqomjedyxspomlkfedczxvrmhezxwtojfdawrpmidbytpkhdazyutsonkfbaxwtqljhdbwvqmhgfaxsqljfdzvqmhfawtrojedaxtolgdayvsqljebxuqlgdywtrpkhfecbzurmjfdbwvqmkidaxvrmlkfeazytpmhedywtqmlhdbzusqlhdzwtqponmljgdayxtrpljhfcbxuplhgfdyvusomigbzurqolkgecayuqmjebyusrplidzwrmjfebxutsnmiecxtsokgebzxusnifeczvrnkhfawvsrnjecavrnkjifdbxtojgezyxvrpokgbyurmlkidbwtqljezyupkfezutqonigbwrplihczxvtsnmkheazuqpmkihcaxutomihczutpkidaytsomjhgcytrpnmhgcawsnlieavsqljhcazyxtomigezutsplgbyvsqnifebwtqplifazytsnlkfcywtqpkjifbxwvronjgbawurmkjidzvupokjiebavsqnjidawtpokhgeayvsqpnjfayvspomjidbzutpkidczvqljhczyvrolkgfcyutpnkfaxvsqpojhfebxupmhecyxtrpnkhdcaxwvsnjg"));  // Output: abcdab
        System.out.println(getSpecialString("voletnazwtqihbymbqkifankbapniawjzymbpftmjawvmhgeztsmzqhulixpncthysjcxtrfxmcwrljbwpkxoecauhwrivtmfbodqpkgwrjhypkgthushaxlgeukcboctgaqlecqmjwrmzyrgvurpkytgysodxskecupfvoesplhgwjcsoiebqhevkjzwvuhesrifbxndbvsidrfxplzmfwurqeaxlhyvivonayrqogtshxpiwkdqexreczyupomkhbxsicwtsoncatmcpibvnganhusojfbretqidcvuhdausoieczriazmedzyohznljztrpexusfytsmzqgeaqkdrjgezspcqoldrhedvuhdbofcwlctkfauocriczymkankhftjwoiaxkjxqkypnmhvjierivmedbpiatgwnjxvqkxwjdqgxumcukzrmlbpdsgutpmdtldvsjewticrhvliwkyxmkevrpgtneyngtmljhfawqocspeujyrpmhwqmfuoldqoldsjebxondvuljylyusgxphyqdcshynbaqfbwvnftnfdasqlctkdslkylatiavtiwujhwphxvnkfdupmaqlhxmkbsnjgxmhbwjbtqmdaphazmcxljbsoigyqnaqdsokbtncsixsmdxskxngarolyxsrplidrpjwmhwjzmasgtpdusgfwsixnctqljwqhyxrhwlkevicthfasfczvneutjdbtkjdzmewmdvldvmcukcrkaredumiandyunetqkxsjzrfslkbyvkbunbpjgyvqmgyxpmereaticqixumhbzsizvupmewrqocvtlhcbzuslyqgwnfbaxrmgtjeazspeutlhgvrgfxkxpmdatrjfwpibzohwmethxlkbpjdzwjcsjhbxskzwkieyqkdvutpeusnivpmlcskdxukiyxkbsfebpdcxmkcyuhwukivjdwojctsmdtnbvresjgxmgbxkcphcrewvmlzpcwlcwolfxwnixkaqhzxvqetkzytjxpkypkaohbridaodthzsjdvsiaqfzvsmjyvibsgdutjzsiewkctgvmawtgecxrgbvjhvumlexunldulypfsnfzmedulysqedsfuqjzrkfbuolhdvmjbqjhftsqdyxldcxnhvokcvtmhgfedvtjzwmhcutqdtnfcbuogxrifdbqjdrlbokevnfdywkfynbpljdcbsixqjaqmzxskczmidrlhbodtlhwvrlaolzmzupmiavkbqonfcbobphxrnfxrgdutrnevncbwmcbwujwuhaunmcpkiwvjzshyrmivtixqkdczrhfcrlgztiypogtkcaqgywpidaoboheulkbtifwrlhxmkcrjyvlgbthgtjfaqebaqljcyqkxogbxmjduondztmaslymievrgtlebwneypdutgujzxwvkawvsqljidxrkevokhedxwojxsjwnecqokzqgtkdaytmjhxmlizoljcaxqkcbyrpkatrhusriylbpkxmiywqifvqojdvlkhfwmkaofedcyumgbqdbyobyrpnaqdymigexolevpkcsfwqjhzylarngbrgyundczndvlzvmgvpiytidxkhwnhwoibarivohzmcpdwkymlzsmapncyvixulhcuhgtqdwvndvnfcvtqhwkztsljdzspjanmlextslgatjxomfsqocqgctmbwqkfayxpdqdbwkcuohfsjankbxwujfyonfsfctqndvphwsnespgypdrfxrqkjdshazqihedwldyodxpecxwtsnfeyqfsgxvkjewvrqpmfzreaqkynmbsomzpndarfbxkeshcvizwukhxtqgaphgxtigebarhdsjdvojzusjiwmivkicrmaocaolheymldxsietnlfywqiwqmftifcyvqofzvukewonlifaqihdburnbolzmbujdumixnarfcyobwlaulzxkdukatkhwjgdcxticzwnivldtojcrpdskjfzrofapmebphyuohgdyslhwmcyvngcuqmbutmlatjwkfcynaromzxtjbpkgzqjczsrjynhwqgyxupfwqjzupfsfzmiaxpeukxkazodspcvumjixlzqmigwjwkewtnfvurpobrexpjgfxuobtpculgaqedrjifvqfyqlfsqkgczwkevrjxnljwlhyodqhedbaqmetgezvsogewsldwrixqocznfbqdayskzwmjapdzpgvmizynibaqiwsrlguqfunguobxsgvieuqfbsrmjiysqgbomcypfbaoidatjhzoftobasjxkgxphgesqfcsihzwofdtliwrohfxuqedrkgewtibrnfvpkbyxmjawrmlywlebqifungxrgxwlyxplftkdzsiwkixsjdyqmcxlyncqedrjftjxwvndwmdwukizrifxpcvngaqiypfebshaujhurivonlbyqmjhdavnizrnmgwkibanlhdcysoivtpkfujarmgtojcvkgayqhdqgunbuifzsohdcukcavpodwjbtjdwkypjihwkxoheuqkxkzmgyljdutrnfvqjxpofsogfdwnetsjcznguiypjdxvtmdqiwtieuieapobypkiwvolkdsnjdvpgdwricpjizmfwnasnkylzvkctnapobywqgzrgwmbuojfsohbxsnftmcxqkedrietkyqfyxnlewribaxlcvsgyutskjazrgfythumjbzuhgtrlixtkbrigwjeczvqkevidtmkeyqesibqletlfuljhymhcangxnmlbokyqizmzvpczsojbyqgaonfcaoftrgbthfxoiwjbzqlavkzrgdcvqerpjztjaxvlkaxwjixkzxwuspkcqfvutrjbazxqjwofsjxocvndxwtmbtgwshuqljculkiawtkaylzxtncbtmlgvthzxuhwuoixuhzymkesievjfskgecrhcpdcbysldvqokdxshaskfbskjiymlhfzuncphzmbvnjzriapmasqmfyohzyqhyndvrjebxojwlbwpogtlywmbrpgetrpodsrfvtgesplgzyonjwldrqofbyldtodwkjcqfaqhxwrixukxncpjhatskeaneysjiyspgxslftncspjduqkbyulicaphvpebvqiyphbzskfxrmidxkhatgxshxkfandqgeawnermcrizrndtncuhbqhgzxpdytqoibxpobrhzsharpeyuidcwnfvpmlynmlgeularkcwqdwonaocqjgtoncphfzoljhebzyrnbrncwutjhurqhatngtkbtkixsncandrftkbtmhfebyvrljxwrkfwrqoburpmdbauljfzvrqmgtspohgdtmernkifaqmkxmkxtqfzniwtjgeypetkjbofzpgbavqfczwnfskculfeyqmcwtgwmeztolyldapgwkavmkcawusrmiaupgtrmgutljbzukxmicplfcbspnfsiavsnmbylcskesqlcpdrhbsfsndzqnaqjzuqmldrjxupmfvonhdunkdthypcrhuonlewkymcrfesojeyvibwuskdzskasftlhfwtihzqdvtnjwshdxmgcaupmarmlgapnjesguqfcyxkayqfdqfvibredxvmesnfdsrfwogzyxumbphfylixsgaqixwvsjfzviedcbupfzxthfbwkgxtkfxvobxtkauneayulierqexofbysngzpjgaxricqizpnfsjxsfwlzqjfvpkgaoieupgcvkyuhwuocrjcujcvmgyviezpnazytlerokearkyxthatibphgfzvmerfwvslzysobrgdxqjymkyoigavkbyrlzwvuniwqogfthazwqjynlywtiyqkjwvrlcunedsnkzxmljyrpcsfcvqmgdytoixtlfwmazwmzvlaymdumlkyqhdrgesqdzvrgxnayujytpcsqpodwvjwkarlearqkzwvqomfcqlapmazqjbviznhbutjzonbxpgujgxlztjfebtsrmlizyuihfdcrexolebznmfxuhysfwpfytqmaolfvrgwsqkgxohzxtncvpctrhvngcwljdwsjxvmaskcymdsrnkhfyxtmldxuhywmzslzvkevphcwkzyokjdrgwvqmashxpnidculaqjfyvlyxrecvqiczpjwojyrhftsmcbxrjbtkzwjapldbtmlhunmanjbtohbyvqewkesgujxplhzmfwulixwkxnihxpdunkxlkcynmkgthuqnaxsreuidwlanjgcbpfvibtjcwqidcbysjzndaojzplawldvmiewumicutncazmexwqexmhxsiaqiatlawukjfuhgtofayphzpedwmjzxpcrpjfvnaqebywunmkawuoctmjzmcyndxndtiavqngcvtpgxwtndvjaocytibayunbwjbqkferjewsqndcxwspgferlfedreuhwsgxrmkzpdbxlkhyqnasqpdyxsrqfwsfcxpfzwrqezojiebzmdwtlzpolgtmhbtmhxwoevrojarfbwngwrqhfdvjdzuqivtlebutheskevqhwqnfapfarndznfxvjwlgapokznkbtsnezumfxshzuhwmbphzsqgcwqlbspgyngbaxoerlkgwpkfvmjwodrphuroifwsjcaqifaxspnhdrmcpkhgapfutjzxkaqnfsfbolecrncrgcwunkigdxkcapocpmczolysncyumcqjwvjhczxlkhxkaytqhbuiexqgcatpebqlfesmcrondarlbxulcayrkhxvjwkhxmzqmanavlcumeztohgwqmlcymanhvqmcaslevnhzobwutpnieylexsriyrjbyqjxlbzvsnexoneuomhbzmlihvthfxtiynharhgvrjgxmjheushewumcphukfyuokzpfergexkbpnkapgvnayuiguspeyojhfyujdwlzwutsgergbphaphzqkxpgbzqihdyxthevncrgcqkapdbwlhgbyldarphbqivqodxrkcxqmfwsnargcumewkfxkheypcbwmauoevuroihzpfdwofdslkznhysneurhvixkjhbpnezmljeshfvifdaxmebyutjxmedwlzvrnmdzytljcrgbvpobaytgftjzxtqgcvjcayobrkhdvuledcxlbynmdaywjizvidsqgetmjebznfviyqifdtslewkctkdarhgvqomlbwrlzokdtojyldyneyrkhvnexvtjytpmgxrpkesjatiburnhdtiznkhfcsiwqlevncqkyobyolfyuomgdwjxkztjcukyqkdymkeczvuixpfwqncasphdaxnjivsgcqkfdbtqocxsljbvkxmkaqplyqmdawsihxplkgboexwuhupjylkyoivuobqobunaocphuicxkazwobztrpfdxumgaqnhcuogtpetpfbwjhzwonbtqkgavtgbuqpfxkjhavrfusqoewlcaujcznanjhfaqlgaodazxmzrjbogasqicqpeskyqjfaskxofvljczperlkhgztgdrniatlgcytsgwuqpcqmdrmevtgfspolebyneuhasfeytlczunjzolfeskhuhxldbupheticatqpodcuhgczmlftjxnevmligtocztlifukbvoduofvsqnkapkgebwokhuqhzohespljcthxwnmjebwlbskaoljwnjftpgdurfvizpnhcrpndyvtqmbxwvpgbrkankcusrmgbonbsqlkjgfdxumiwpmdrpeywtpjaofcwjbwlhyrmhgfukhzogvthusfuqebvrnjxlgavojgwldsfthusrgfupongukytjcyqgtqjdtkzrjbsjazrfyspkhdavkhancukeukdqdyusjeusgwupkgavjxqgfbxmgxmcxomeazpdvpespcaxpixsobuidtohzmhearqocxuobsjidzvtjiaplixsjxplgvqjesphdbwobzsgxrfdylbzmgbztjaywsjcqnldcqnavlzxkecbukhbwkymftlicunlixqfwsqpmlkeznifdyunbvogwrfbtigfuqjcphfewukganhytqnfcyspcspicanctreaqnizutqiyusgzpnlbrogxkbzohzyxkjdqmevneylbxreawqihulyuojhysrkavmlfulkfxpmgvlexqdskxuogetspdrfsigecqdaspcqoduizundyofcbxskysjesqfzwjzmgthanlyvkftnewqgvtlznidbpjfulbskyvjwriavtlbuqkcrpnasqmlkapfbumaxqgvtnibpjwmztgtkcvkgtobunczrhyrqngytgcvmbpftgfvixuqgzmlkbviarkdzrqmayqiwviwqpmgerhaobzyumgduibpfzwsjhgfbtnkfangetrkerkczmizoedvrnfctjfzocupmdtqedukjxnhupdsrmfcvodxvjetifdushzsljynapcwplfbsrqmbphwrqfcrqhwkfcsnazqjbsqfypeukcqigwpcurexusohupgtmdwupolexsgyoebxuiwnmjbokzyvsodtivtokyxkanfwpjdbqguihaxlavljzvjcqlzndcyvqjgfvrfbohgutmatkaxkapezuhcaqolifsrpkigznfdzobwpkfwodwvlfdvnivjxwmjztmermkbvjdatguobxmdwqhwqpjcznhujbtlzwnicvuhxpgtrjwvoeazrniwkxoiyvokzxsodqiwjgbpfynedsiylidbombrpkxurpdxsifzuoixsmlbphywslbysnjfwjaxvkgfzqixvlfyofavtroiwtpibqfytkiandtqetohzqpcqeylcwmfwshcatgyrncbzvlfywuongzpfapdqojhawqpjankcsmhfdvtnmcuidtkznjzsphxuojbynlbuiywngtjibwkywuhdczvmgbwuietgcpjyunmgxndvtogbznhxnkhwnesrndzxmhfywvkaonczxoiznjzyoguqljwvifapgxlcwrnmdxtphesnmgtpgyncsmaxredyulfxshbaoerqdqgdxqifdtkargxutleyliargaojyxwtihdsmaqfexmdcbwkdtogumjaolhectgatofyxsjdsfdtokyujandzurmhvpodslazvpobodtkzrlylgfslzxsfdctiwsrnkjwlhcasqnkhbzrlapogzvpdshbqdwkivsjdwjwohytheuqjcsrqmgurplbyqdypesgezuqiwnigvulihgxspdxumkigumgyvkfwqectkecznhulynigukcvtieuqesgtnavtkdcarongujdzxuqfysmcyxnkynaqjihapiwlfaywtsfbtlharkhfxmcsqfezwqkcrlecxnedrqkxnfxmbqkgwpjwuqguqmeusiaokjxpkbxthcqebvndyxpkfxuljhyvndqojdxnlbplkfvuhxkfzwvrhasfxtjxvrhzwlkysgdridvqkguskdthzncbrjgcbviesofbpdqkymjytqiwpjyxnmhynbaqndbwlztpcbtifcxonbysmfzyvneatniviwobulypgtshfaomihaqpnewrqecxurnewjypkiyoedunmkeyumavkfdcyqdrodaumzmarneuhzmjexwrkgbwkzuplesjewojhwmlkcwmzpjiczslatjixlerfczvobwtojcztqhfaywokjfzmjgwrhdwlavrmbplcsgbytjfwkjgusrkcautkezpigbxrmbathetohbqhavoguicxtrifedcyqphasgulgfwkjythzojatpmztgwplfsnanivrhbqnhvkcpiyljhuhxrjyngfypexmayogfsldsqkihyodtnlhcxojazrfwondarnkyrqjcwvqebpcwuhwpdypetpljizqfvpohfcyutqjxrjerqhyxmasfuleushynlzyuhbyxureanbukfcurokhdrfyvievjazmihfumgdreutkbodtkgbxnicbxskjetpmzyujytricyurexogbsphxpkcxqmigzymljhwmbrnarphxvjyrpdtoiftplyrhwtkjypdzmzujyuqncxolzniwlkcariczuqfvidaqetnhetjeujbpixrpftrebvreanfbandcwngdcukfbtljzsniawtmhxkysmgwmecbpofxrlgxrfticvkdvjguqlcupcsigyvuqdthulaztiwlkcpjyvjdyrplbodsgbaxmgwjfwokyrqlidwlznbqdunhayvplgbapfukfdsnbpdrfvjfzrgbsonmlyomkfzplhfsqgfcsqdvkhuoesnjdcplhgezxujwpevqlkjxrqhevnihgyoivlhymfbzqmhdxwofzpkeskxogdvtmzrewlbylkgbyobpexwupoidztlgdvsrpesgwrlzvmhfdztjbazqgxtpkixsheaynancbsmawqhdypgduhcpjdtrigarkbukyskdtgasnicxmezwpfbslznatrjecphwvqgduoiymicroiwlhyumzupnhcphcsgcqgetmzofymdarqpedqfdzrpfwpjhvogxwvqfzqnkdulhezvplavrerofvrgavsqmeypivkeyvmkxukibriznmgaqkbxmaohxvtkcqpicqncylcxlbxmgaoecarlkfulexojhbrniytihuhazobtocpfyqgzofvkdureyplkxqdvtrihbzwnidulkidtoixocavjdtomzwpkfcaoetjevigezmczmzqkdtocasmedwkhaogumgxsnlzogujcrpdsnbumlivtjivjgcpodavlhywrqdaoczvkaujcwtgaukdvjaolbogtrhcxwnetrfwrlaoiymgaumiathbxqnaxnecrfaymewsrldsqliesqeuokcqifxrjgbaymzpcwjyxpmlyojdxrpcxoeynfbpgdcsjezqdwqoczpgtgevtpfukhwqhergtqhazsgwviebrqdynigcrmigwogxndrkfxujxmlyxukeskdayljyrqheavjgvmbsnbsftjcromigftobqnlaxkdxliesncvtgdrmjgcrncslfurnazneumdqjiwvqfyvslbolerkawretpkihetqfxsgcysohgebzyskfypnawtjiwkfebthbxmcplhuokeyrpcvtivqjidqmgtsmgutibynctgyxnbsjieythzqdvigwngvnkzusrhwljidytsmlfylcrezrkcrmzyokbrexpngbuqiyulczskbvnlcbzwtmjwkivrplkzxpmavofwrkgzqodywrhylhfbanbtidbtsgawpgzplkatqpldyvqmcvuolihxmkaredqhcbvsomzsofeulburpedyqheyqfewtnlixqmkcyndrfdvofzsgbxkhvoliysohcumfbxrlbynawkixrkysoctqndxmfdvlkdvqhxrgcxwnhzqgbpojbtrizuqlidskjdytlkxplaviypkhctpcpdsfuiczwphedqdtpjbulymbuherguibsqnhayqnmgfslkhzxqieuhcasiazqmgvnbsldunhvrmivqidqeshzpgvpibofdsfbztsnaqpewqedsjhwnfdxqmbuphurkdrncvmkfaondayrecrpoczyogyomkjibyusfebasqeshvsixqgtkgtleutidzrgdavukfwqjhvjfzxomzyrmevuokfshcrfvrgezvjynkjgvpfxqlexvrngeyljftnleypieargtgxpctrebwrlicvtsmjawkiaxvmztsgwpfwkfdwpncyqhdzvpodbodctoiylynavibqjgewnmcuqnhfuplkzyrljyusoeavsrhuierojiesoiyujyuhyphfvujztmjiyofzmkedxkcvkhxnmeankfwutihysjbokhfasftohgeazqgwjzodqncvomfypieduolcaxvjxsmbqkfetkjhvnldzvrkhfdrkidaodbuodunctqleangbvkywqhbriwjhuhwpofxkzsjwjctkxvonmgureaynmeylgwmjcvmjdsogthusldxkgfspmftrjfcznlftjcbsizmbrgbofztkcthfwsidcspihdxvlavtkgcwuiwthypdtqgdslfumkivrpdcbzqmkjaqfzpjharfunhupdazrifaywuhga"));



    }
}
