public class Palindrome {
    /** Returns a Deque where the characters appear in the same order as in the given String. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque= new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    /** Returns true if the given word is a palindrome, and false otherwise. */
    private boolean isPalindrome(Deque<Character> wordDeque) {
        while (wordDeque.size() > 1) {
            return wordDeque.removeFirst() == wordDeque.removeLast() && isPalindrome(wordDeque);
        }
        return true;
    }

    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    /**  Returns true if the word is a palindrome according to the character comparison test
     * provided by the CharacterComparator passed in as argument cc.  */
    private boolean isPalindrome(Deque<Character> wordDeque, CharacterComparator cc) {
        while (wordDeque.size() > 1) {
            return cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast()) && isPalindrome(wordDeque, cc);
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }
}

