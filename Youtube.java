import javax.swing.text.rtf.RTFEditorKit;

import tester.*;

class User {
    String username;
    String displayName;

    User(String username, String displayName) {
        this.username = username;
        this.displayName = displayName;
    }
}

interface Comment {

public boolean isCommentByAuthor(User author);
public int totalLikes();
public String unrollCommentThread();

}

class VideoComment implements Comment {

    String text;
    int likes;
    int replies;
    User author;

    VideoComment(String text, int likes, int replies, User author) {
        this.text = text;
        this.likes = likes;
        this.replies = replies;
        this.author = author;
    }
    
    public boolean isCommentByAuthor(User author) {
        return this.author == author;
    }

    public int totalLikes() {
        return this.likes;
    }

    public String unrollCommentThread() {
        return this.author.username + '\n' +
        this.likes + " likes" + '\n' +
        this.replies + " replies" + '\n' +
        this.text + '\n'; 

    }

    public int totalInteractions() {
        return this.replies + this.likes;
    }

}

class ReplyComment implements Comment {

    String text;
    int likes;
    User author;
    Comment replyTo;

    ReplyComment(String text, int likes, User author, Comment replyTo) {
        this.text = text;
        this.likes = likes;
        this.author = author;
        this.replyTo = replyTo;
    }

    public boolean isCommentByAuthor(User author) {
        return author == this.author && replyTo.isCommentByAuthor(author);
    }

    public int totalLikes() {
        return this.likes + replyTo.totalLikes();
    }

    
    public String unrollCommentThread() {
        return replyTo.unrollCommentThread() + '\n' +
        this.author.username + '\n' + 
        this.likes + " likes" + '\n' +
        this.text + '\n';
    }

}

class Youtube {

    User u1 = new User("SammyNA", "Sammy");
    User u2 = new User("DanielaNA", "Daniela");
    User u3 = new User("SomePersonn ", "Personnnn");
    User u4 = new User("John Doe", "John");

    VideoComment vc1 = new VideoComment("Yoo what's going on peoples", 100, 2, u1);
    VideoComment vc2 = new VideoComment("Having a really good day!", 2, 1, u2);

    ReplyComment rc1 = new ReplyComment("Not much, you?", 20, u2, vc1);
    ReplyComment rc2 = new ReplyComment("Hey, it's late, get off Youtube!", 20, u3, rc1);
    ReplyComment rc3 = new ReplyComment("I hope that doesn't keep up!", 1203, u1, vc2);
    ReplyComment rc4 = new ReplyComment("Replying to my self", 222, u1, vc1);
    ReplyComment rc5 = new ReplyComment("Keep this thread going", 54, u4, rc1);

    String thing = rc5.unrollCommentThread();

    void testAuthorIsCommenting(Tester T) {
        T.checkExpect(vc1.isCommentByAuthor(vc1.author), true);
        T.checkExpect(vc1.isCommentByAuthor(vc2.author), false);
    }

    void testTotalLikes(Tester T) {
        T.checkExpect(vc1.totalLikes(), 100);
        T.checkExpect(vc2.totalLikes(), 2);
    }

    void testUrollCommentThread(Tester T) {
        T.checkExpect(vc1.unrollCommentThread(), "SammyNA" + '\n' +
                                                    100 + " likes" + '\n' +
                                                    2 + " replies" + '\n' +
                                                    "Yoo what's going on peoples" + '\n');

        T.checkExpect(vc2.unrollCommentThread(), "DanielaNA" + '\n' +
                                                    2 + " likes" + '\n' +
                                                    1 + " replies" + '\n' +
                                                    "Having a really good day!" + '\n');
    }

    void testTotalInteractions(Tester T) {
        T.checkExpect(vc1.totalInteractions(), 102);
        T.checkExpect(vc2.totalInteractions(), 3);
    }

    void testReplyAuthorIsCommenting(Tester T) {
        T.checkExpect(rc4.isCommentByAuthor(rc4.author), true);
        T.checkExpect(rc4.isCommentByAuthor(rc2.author), false);
    } 

    void testReplyTotalLikes(Tester T) {
        T.checkExpect(rc3.totalLikes(), 1205);
        T.checkExpect(rc1.totalLikes(), 120);
    }

    // void testReplyUnrollCommentThread(Tester T) {
        // T.checkExpect(rc1.unrollCommentThread(), "SammyNA" + '\n' +
                                                    // 100 + " likes" + '\n' +
                                                    // 2 + " replies" + '\n' +
                                                    // "Yoo what's going on peoples" + '\n' +
                                                    // "DanielaNA" + '\n' +
                                                    // 20 + " likes" + '\n' +
                                                    // "Not much, you?" + '\n');
// 
    
    // }



}


