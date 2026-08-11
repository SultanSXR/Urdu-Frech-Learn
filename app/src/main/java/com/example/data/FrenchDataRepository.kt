package com.example.data

import com.example.data.models.ConversationScenario
import com.example.data.models.DialogueLine
import com.example.data.models.Flashcard
import com.example.data.models.FrenchAlphabetItem
import com.example.data.models.Lesson
import com.example.data.models.LessonCard
import com.example.data.models.LessonQuestion

object FrenchDataRepository {

    val alphabets: List<FrenchAlphabetItem> = listOf(
        FrenchAlphabetItem("A a", "ah", "Ah (like 'a' in father)", "آ (آب کی طرح)", "اردو 'آ' کی طرح صاف اور لمبی آواز۔", "Avion", "Airplane", "ہوائی جہاز", "Open mouth wide", "منہ پورا کھول کر بولیں"),
        FrenchAlphabetItem("B b", "bé", "Bay", "بے", "اردو حرف 'ب' یا 'بے' کی آواز۔", "Bonjour", "Hello", "سلام / صبح بخیر", "Lips together then release", "ہونٹ ملا کر چھوڑیں"),
        FrenchAlphabetItem("C c", "sé", "Say (soft S before E/I)", "سے", "اردو 'س' کی طرح 'ای' یا 'اے' سے پہلے۔", "Café", "Coffee / Café", "کافی ہاؤس", "Hard 'K' sound before A/O/U", "الف، و، یا کے سامنے 'ک' کی آواز"),
        FrenchAlphabetItem("D d", "dé", "Day", "دے", "اردو 'د' (دال) کی نرم آواز، انگریزی D کی طرح سخت نہیں۔", "Dent", "Tooth", "دانت", "Tongue behind top teeth", "زبان دانتوں کی پشت پر رکھیں"),
        FrenchAlphabetItem("E e", "uh", "Uh (like 'e' in the)", "عہ / اوہ", "اردو زبر کے ساتھ ہلکا سا 'عہ'۔", "Eau", "Water", "پانی", "Rounded lips, relaxed tongue", "ہونٹ گول اور زبر کا تلفظ"),
        FrenchAlphabetItem("É é", "ay", "Ay (like 'ay' in day)", "اے (مجہول)", "اردو مجہول 'ے' جیسے 'دے' یا 'لے'۔", "École", "School", "اسکول", "Smile while saying 'ay'", "مسکراتے ہوئے 'اے' بولیں"),
        FrenchAlphabetItem("È è / Ê ê", "eh", "Eh (like 'e' in bed)", "اے (مفتوح)", "اردو میں 'اے' کا مفتوح تلفظ جیسے 'شیر'۔", "Père", "Father", "والد / باپ", "Open mouth slightly wider", "منہ تھوڑا سا زیادہ کھولیں"),
        FrenchAlphabetItem("F f", "effe", "Eff", "ایف", "اردو 'ف' (فے) کی آواز۔", "Fleur", "Flower", "پھول", "Upper teeth on lower lip", "اوپر کے دانت نیچے کے ہونٹ پر"),
        FrenchAlphabetItem("G g", "gé", "Zhay (soft zh)", "ژے (ژالہ کی طرح)", "اردو کا خاص حرف 'ژ' (جیسے ژالہ باری)۔ اردو دانوں کے لیے انتہائی آسان!", "Gâteau", "Cake", "کیک", "Soft G like 's' in measure", "اردو حرف 'ژ' یاد رکھیں"),
        FrenchAlphabetItem("H h", "hache", "Silent in French!", "خاموش (پڑھا نہیں جاتا)", "فرانسیسی میں 'H' پڑھا نہیں جاتا، خاموش رہتا ہے۔", "Homme", "Man", "آدمی", "Always silent! Pronounce next vowel", "ہمیشہ خاموش! اگلے واول سے شروع کریں"),
        FrenchAlphabetItem("I i", "ee", "Ee (like 'ee' in see)", "ای (معروف)", "اردو زیر کے ساتھ لمبی 'ای'۔", "Image", "Picture", "تصویر", "High smile sound", "تیز مسکراہٹ کے ساتھ 'ای'"),
        FrenchAlphabetItem("J j", "ji", "Zhee", "ژی", "اردو 'ژ' کے ساتھ 'ای' کی آواز۔", "Jardin", "Garden", "باغ", "Soft Zh sound like 'measure'", "ژ سے ژی بولیں"),
        FrenchAlphabetItem("K k", "ka", "Kah", "کا", "اردو 'ک' (کاف) کی آواز۔", "Kangourou", "Kangaroo", "کینگرو", "Hard K sound", "سخت کاف کی آواز"),
        FrenchAlphabetItem("L l", "elle", "El", "ایل", "اردو 'ل' (لام) کی آواز۔", "Lune", "Moon", "چاند", "Tongue tip on teeth ridge", "زبان کی نوک اوپر لگائیں"),
        FrenchAlphabetItem("M m", "emme", "Em", "ایم", "اردو 'م' (میم) کی آواز۔", "Maison", "House", "مکان / گھر", "Lips closed tight", "ہونٹ بند رکھیں"),
        FrenchAlphabetItem("N n", "enne", "En", "این", "اردو 'ن' (نون)۔ اگر لفظ کے آخر میں ہو تو 'ن٘' (نون غنہ) بن جاتا ہے!", "Nuit", "Night", "رات", "Standard N or nasal in vowels", "اردو کا نون غنہ 'ن٘' یاد رکھیں"),
        FrenchAlphabetItem("O o", "oh", "Oh (like 'o' in go)", "او", "اردو 'او' پیش کے ساتھ۔", "Oiseau", "Bird", "پرندہ", "Round lips tightly", "ہونٹ گول رکھیں"),
        FrenchAlphabetItem("P p", "pé", "Pay", "پے", "اردو 'پ' (پے) کی آواز۔", "Pain", "Bread", "روٹی / بریڈ", "Soft P, non-aspirated", "نرم پے کی آواز"),
        FrenchAlphabetItem("Q q", "koo", "Koo (pursed lips)", "کو (باریک)", "اردو 'ک' کے بعد باریک 'و'۔", "Quatre", "Four", "چار", "Usually followed by U, pronounced K", "فرانسیسی میں K کی طرح پڑھا جاتا ہے"),
        FrenchAlphabetItem("R r", "erre", "Ehrr (Guttural throat sound)", "ایغ (غین کی طرح)", "اردو کا 'غ' (غین) یا 'خ'۔ انگریزی کے پاس یہ آواز نہیں مگر اردو میں 'غائب' اور 'غریب' کا 'غ' بالکل فرانسیسی R ہے!", "Rouge", "Red", "سرخ / لال", "Gargle at the back of throat like Urdu 'غ'", "گلے کی گہرائی سے 'غ' کی آواز نکالیں"),
        FrenchAlphabetItem("S s", "esse", "Ess", "ایس", "اردو 'س' (سین)۔ دو واول کے درمیان 'ز' بنتا ہے۔", "Soleil", "Sun", "سورج", "S or Z between vowels", "سين یا زیڈ کی آواز"),
        FrenchAlphabetItem("T t", "té", "Tay", "تے", "اردو 'ت' (تے) کی نرم آواز، انگریزی T کی طرح سخت نہیں۔", "Tasse", "Cup", "پیالی", "Soft T with tongue on front teeth", "نرم تے کی آواز"),
        FrenchAlphabetItem("U u", "oo", "French 'U' (Pursed lips 'ee')", "او (باریک/پٹی ہوئی)", "ہونٹ 'او' کی شکل میں بنا کر 'ای' بولیں!", "Un", "One", "ایک", "Purse lips like saying 'oo' but say 'ee'", "ہونٹ گول کر کے 'ای' بولیں"),
        FrenchAlphabetItem("V v", "vé", "Vay", "وے", "اردو 'و' کی آواز (دانت اور ہونٹ)۔", "Ville", "City", "شہر", "Top teeth on bottom lip", "دانت نیچے والے ہونٹ پر"),
        FrenchAlphabetItem("W w", "double vé", "Doob-luh vay", "ڈبل وے", "اردو ڈبل وے کی آواز۔", "Wagon", "Carriage", "گاڑی / ڈبہ", "Pronounced like V in French", "فرانسیسی میں V کی طرح پڑھیں"),
        FrenchAlphabetItem("X x", "iks", "Iks", "ایکس", "اردو 'کس' یا 'گز'۔", "Xylophone", "Xylophone", "زائیلوفون", "Ks or Gz sound", "کس یا گز کی آواز"),
        FrenchAlphabetItem("Y y", "i grec", "Ee-grek", "ای گریک", "اردو 'ای' یا 'ی'۔", "Yeux", "Eyes", "آنکھیں", "Sounds like 'ee'", "ای کی آواز"),
        FrenchAlphabetItem("Z z", "zède", "Zed", "زیڈ", "اردو 'ز' (زے) کی آواز۔", "Zèbre", "Zebra", "زیبرا", "Buzzing Z sound", "زے کی آواز"),
        FrenchAlphabetItem("Ç ç", "C cédille", "S sound before A,O,U", "سین (سیڈیلی)", "اردو 'س' کی آواز، خاص طور پر A, O, U سے پہلے۔", "Garçon", "Boy", "لڑکا", "Makes C soft (S sound)", "سی کو نرم 'س' بناتا ہے")
    )

    val lessons: List<Lesson> = listOf(
        Lesson(
            id = 1,
            titleEn = "Greetings & Politeness",
            titleUr = "سلام اور آداب",
            descriptionEn = "Master French hellos, goodbyes, and polite courtesy phrases.",
            descriptionUr = "فرانسیسی سلام، خدا حافظ اور آداب کے بنیادی جملے سیکھیں۔",
            iconName = "waving_hand",
            cards = listOf(
                LessonCard(
                    titleEn = "Hello / Good Morning",
                    titleUr = "سلام / صبح بخیر",
                    frenchPhrase = "Bonjour",
                    phoneticEn = "Boh-zhoor",
                    phoneticUr = "بونژور (ن٘ نکیلی آواز)",
                    meaningEn = "Hello / Good morning / Good day",
                    meaningUr = "سلام / صبح بخیر",
                    urduSoundNote = "اردو کا 'ژ' (جیسے ژالہ) اور 'ن٘' (نون غنہ) مل کر بالکل صحیح فرانسیسی Pronunciation بناتے ہیں!",
                    audioText = "Bonjour"
                ),
                LessonCard(
                    titleEn = "Good Evening",
                    titleUr = "شام بخیر",
                    frenchPhrase = "Bonsoir",
                    phoneticEn = "Boh-swahr",
                    phoneticUr = "بونسوار (غین/ر کی آواز)",
                    meaningEn = "Good evening",
                    meaningUr = "شام بخیر",
                    urduSoundNote = "آخر میں 'ر' کو گلے سے 'غ' کی طرح نرم پڑھیں۔",
                    audioText = "Bonsoir"
                ),
                LessonCard(
                    titleEn = "How are you? (Polite)",
                    titleUr = "آپ کیسے ہیں؟",
                    frenchPhrase = "Comment allez-vous?",
                    phoneticEn = "Koh-mah tah-lay voo?",
                    phoneticUr = "کوموں تالے وو؟",
                    meaningEn = "How are you? (Formal/Polite)",
                    meaningUr = "آپ کا کیا حال ہے؟ (باقاعدہ)",
                    urduSoundNote = "کوموں کا 'ں' نون غنہ ہے اور 'تالے' میں 'ت' اردو تال کی طرح نرم ہے۔",
                    audioText = "Comment allez-vous?"
                ),
                LessonCard(
                    titleEn = "Please",
                    titleUr = "برائے مہربانی",
                    frenchPhrase = "S'il vous plaît",
                    phoneticEn = "Seel voo play",
                    phoneticUr = "سیل وو پلے",
                    meaningEn = "Please (Formal)",
                    meaningUr = "برائے مہربانی / پلیز",
                    urduSoundNote = "سیل وو پلے فرانسیسی میں ہر بات کے بعد کہا جاتا ہے۔",
                    audioText = "S'il vous plaît"
                ),
                LessonCard(
                    titleEn = "Thank you very much",
                    titleUr = "بہت بہت شکریہ",
                    frenchPhrase = "Merci beaucoup",
                    phoneticEn = "Mair-see boh-koo",
                    phoneticUr = "میرسی بوکو",
                    meaningEn = "Thank you very much",
                    meaningUr = "آپ کا بہت شکریہ",
                    urduSoundNote = "میرسی میں 'ر' گلے سے اور بوکو میں 'کو' صاف ہندی/اردو کاف ہے۔",
                    audioText = "Merci beaucoup"
                )
            ),
            questions = listOf(
                LessonQuestion(
                    id = 101,
                    questionEn = "How do you say 'Hello' or 'Good morning' in French?",
                    questionUr = "فرانسیسی میں 'سلام' یا 'صبح بخیر' کیسے کہتے ہیں؟",
                    options = listOf("Bonjour", "Bonsoir", "Merci", "Au revoir"),
                    correctAnswerIndex = 0,
                    explanationEn = "'Bonjour' means Hello or Good morning in French.",
                    explanationUr = "'Bonjour' (بونژور) کا مطلب فرانسیسی میں سلام یا صبح بخیر ہوتا ہے۔",
                    audioTargetFrench = "Bonjour"
                ),
                LessonQuestion(
                    id = 102,
                    questionEn = "What is the Urdu-phonetic match for 'Merci beaucoup'?",
                    questionUr = "'Merci beaucoup' کا صحیح اردو تلفظ کیا ہے؟",
                    options = listOf("میرسی بوکو", "بونسوار", "سیل وو پلے", "کوموں تالے وو"),
                    correctAnswerIndex = 0,
                    explanationEn = "'Merci beaucoup' sounds like 'میرسی بوکو' and means thank you very much.",
                    explanationUr = "'Merci beaucoup' کا تلفظ 'میرسی بوکو' ہوتا ہے جس کا مطلب 'بہت شکریہ' ہے۔",
                    audioTargetFrench = "Merci beaucoup"
                ),
                LessonQuestion(
                    id = 103,
                    questionEn = "Select the phrase that means 'Please' in polite French:",
                    questionUr = "فرانسیسی میں 'برائے مہربانی' کے لیے کون سا جملہ استعمال ہوتا ہے؟",
                    options = listOf("Bonjour", "Au revoir", "S'il vous plaît", "Pardon"),
                    correctAnswerIndex = 2,
                    explanationEn = "'S'il vous plaît' (سیل وو پلے) is used for 'Please'.",
                    explanationUr = "'S'il vous plaît' کا مطلب 'برائے مہربانی' ہوتا ہے۔",
                    audioTargetFrench = "S'il vous plaît"
                )
            )
        ),
        Lesson(
            id = 2,
            titleEn = "Numbers & Counting",
            titleUr = "گنتی اور اعداد",
            descriptionEn = "Learn French numbers 1 to 10 with clear audio and phonetic pronunciation.",
            descriptionUr = "فرانسیسی میں 1 سے 10 تک گنتی سیکھیں۔",
            iconName = "format_list_numbered",
            cards = listOf(
                LessonCard("One (1)", "ایک (1)", "Un", "Uh-n", "عہ / اَں (نون غنہ)", "One (1)", "ایک", "اردو نون غنہ 'ن٘' کا بالکل صحیح استعمال", "Un"),
                LessonCard("Two (2)", "دو (2)", "Deux", "Duh", "دو (باریک)", "Two (2)", "دو", "دال کی نرم آواز", "Deux"),
                LessonCard("Three (3)", "تین (3)", "Trois", "Trwah", "تروا (تھوڑا غین کے ساتھ)", "Three (3)", "تین", "ت کی نرم آواز + غوا", "Trois"),
                LessonCard("Four (4)", "چار (4)", "Quatre", "Kah-truh", "کاطر / کاتخ", "Four (4)", "چار", "کاف کے بعد نرم ت اور گلے کی ر", "Quatre"),
                LessonCard("Five (5)", "پانچ (5)", "Cinq", "Sank", "سینک", "Five (5)", "پانچ", "سين کی تیز آواز", "Cinq"),
                LessonCard("Ten (10)", "دس (10)", "Dix", "Dees", "دیس", "Ten (10)", "دس", "داس/دیس کی نرم آواز", "Dix")
            ),
            questions = listOf(
                LessonQuestion(
                    id = 201,
                    questionEn = "What is the French word for '3' (Three)?",
                    questionUr = "فرانسیسی میں '3' (تین) کو کیا کہتے ہیں؟",
                    options = listOf("Un", "Trois", "Deux", "Cinq"),
                    correctAnswerIndex = 1,
                    explanationEn = "'Trois' (تروا) means 3 in French.",
                    explanationUr = "'Trois' (تروا) فرانسیسی میں 3 کو کہتے ہیں۔",
                    audioTargetFrench = "Trois"
                ),
                LessonQuestion(
                    id = 202,
                    questionEn = "Which number is 'Cinq' in French?",
                    questionUr = "'Cinq' (سینک) کون سا عدد ہے؟",
                    options = listOf("4", "5", "10", "1"),
                    correctAnswerIndex = 1,
                    explanationEn = "'Cinq' equals 5.",
                    explanationUr = "'Cinq' کا مطلب 5 ہے۔",
                    audioTargetFrench = "Cinq"
                )
            )
        ),
        Lesson(
            id = 3,
            titleEn = "Food & Dining Out",
            titleUr = "کھانا اور ہوٹل",
            descriptionEn = "Order water, tea, coffee, and delicious meals in a French restaurant.",
            descriptionUr = "فرانسیسی ریستوران میں پانی، چائے، کافی اور کھانا آرڈر کرنا سیکھیں۔",
            iconName = "restaurant",
            cards = listOf(
                LessonCard("Water", "پانی", "L'eau", "Loh", "لو (لوہا کی طرح)", "Water", "پانی", "اردو لو کی طرح سادہ آواز", "L'eau"),
                LessonCard("Bread", "روٹی / بریڈ", "Le pain", "Luh pah-n", "لو پاں (نون غنہ)", "Bread", "روٹی / بریڈ", "پاں میں نون غنہ ن٘ استعمال کریں", "Le pain"),
                LessonCard("Coffee", "کافی", "Le café", "Luh kah-fay", "لو کافی", "Coffee", "کافی", "کافی کی واضح آواز", "Le café"),
                LessonCard("The menu, please", "مینوا، برائے مہربانی", "Le menu, s'il vous plaît", "Luh muh-nyoo, seel voo play", "لو مینو، سیل وو پلے", "The menu, please", "مینو کارڈ، برائے مہربانی", "ریستوران میں پہلا جملہ!", "Le menu, s'il vous plaît"),
                LessonCard("The bill, please", "بل، برائے مہربانی", "L'addition, s'il vous plaît", "Lah-dee-syoh, seel voo play", "لادیسیوں، سیل وو پلے", "The bill, please", "بل لائیے برائے مہربانی", "کھانے کے بعد بل مانگنا", "L'addition, s'il vous plaît")
            ),
            questions = listOf(
                LessonQuestion(
                    id = 301,
                    questionEn = "How do you ask for 'The bill, please' in French?",
                    questionUr = "فرانسیسی میں 'بل لائیے برائے مہربانی' کیسے کہیں گے؟",
                    options = listOf("L'addition, s'il vous plaît", "Bonjour", "Le pain", "Au revoir"),
                    correctAnswerIndex = 0,
                    explanationEn = "'L'addition, s'il vous plaît' is used to request the bill.",
                    explanationUr = "'L'addition, s'il vous plaît' (لادیسیوں، سیل وو پلے) کا مطلب بل مانگنا ہے۔",
                    audioTargetFrench = "L'addition, s'il vous plaît"
                )
            )
        ),
        Lesson(
            id = 4,
            titleEn = "Travel & Asking Directions",
            titleUr = "سفر اور راستے کا پوچھنا",
            descriptionEn = "Find your way around Paris, train stations, hotels, and airports.",
            descriptionUr = "پیرس میں راستے، ریلوے اسٹیشن اور ہوٹل تلاش کرنے کے اہم جملے۔",
            iconName = "explore",
            cards = listOf(
                LessonCard("Where is...?", "کہاں ہے...؟", "Où est...?", "Oo ay...?", "او اے...؟", "Where is...?", "... کہاں ہے؟", "او اے کے بعد جگہ کا نام کہیں", "Où est...?"),
                LessonCard("The train station", "ریلوے اسٹیشن", "La gare", "Lah gahr", "لا گار", "The train station", "ریلوے اسٹیشن", "گار میں گلے سے ر بولیں", "La gare"),
                LessonCard("Where is the bathroom?", "باتھ روم کہاں ہے؟", "Où sont les toilettes?", "Oo soh lay twah-let?", "او سوں لے توالیٹ؟", "Where is the restroom?", "باتھ روم کہاں ہے؟", "سیاحت کے لیے سب سے ضروری جملہ!", "Où sont les toilettes?"),
                LessonCard("Straight ahead", "سیدھا آگے", "Tout droit", "Too drwah", "تو دروا / تو غوا", "Straight ahead", "سیدھا آگے", "دروا میں دال نرم اور غین کی آواز", "Tout droit")
            ),
            questions = listOf(
                LessonQuestion(
                    id = 401,
                    questionEn = "How do you ask 'Where is the train station?'",
                    questionUr = "'ریلوے اسٹیشن کہاں ہے؟' فرانسیسی میں کیسے پوچھیں گے؟",
                    options = listOf("Où est la gare?", "Merci beaucoup", "Je m'appelle", "C'est combien?"),
                    correctAnswerIndex = 0,
                    explanationEn = "'Où est la gare?' means Where is the train station?",
                    explanationUr = "'Où est la gare?' (او اے لا گار) کا مطلب ریلوے اسٹیشن کا پوچھنا ہے۔",
                    audioTargetFrench = "Où est la gare?"
                )
            )
        ),
        Lesson(
            id = 5,
            titleEn = "French Culture & Etiquette",
            titleUr = "فرانسیسی تہذیب اور آداب",
            descriptionEn = "Understand French social customs, 'La Bise', formal vs informal address, and dining etiquette.",
            descriptionUr = "فرانسیسی معاشرتی آداب، بوسہٴ گال (La Bise)، آپ اور تم کا فرق اور کھانے کا طریقہ سیکھیں۔",
            iconName = "groups",
            cards = listOf(
                LessonCard(
                    titleEn = "Cheek Kiss Greeting (La Bise)",
                    titleUr = "گال کا مسنون سلام (لا بیز)",
                    frenchPhrase = "Faire la bise",
                    phoneticEn = "Fair lah beez",
                    phoneticUr = "فیر لا بیز",
                    meaningEn = "To give cheek kisses in greeting",
                    meaningUr = "سلام کرتے وقت گال سے گال ملانا",
                    urduSoundNote = "فرانس میں دوستوں اور رشتہ داروں کو ملتے وقت 2 سے 4 بار گال ملائے جاتے ہیں۔",
                    audioText = "Faire la bise"
                ),
                LessonCard(
                    titleEn = "Formal 'You' vs Informal 'You'",
                    titleUr = "آپ (Vous) بمقابلہ تم (Tu)",
                    frenchPhrase = "Vous vs Tu",
                    phoneticEn = "Voo vs Too",
                    phoneticUr = "وو (آپ) اور تو (تم)",
                    meaningEn = "'Vous' is respectful (آپ), 'Tu' is casual (تم)",
                    meaningUr = "اجنبیوں، بزرگوں اور دکان داروں کے لیے 'Vous' (وو) بولیں!",
                    urduSoundNote = "اردو کی طرح فرانسیسی میں بھی 'آپ' اور 'تم' میں واضح فرق موجود ہے۔",
                    audioText = "Vous"
                ),
                LessonCard(
                    titleEn = "Enjoy your meal!",
                    titleUr = "کھانا مبارک / بسم اللہ",
                    frenchPhrase = "Bon appétit!",
                    phoneticEn = "Boh nah-pay-tee!",
                    phoneticUr = "بون اپیتی!",
                    meaningEn = "Enjoy your meal / Bon appétit",
                    meaningUr = "کھانے سے لطف اندوز ہوں!",
                    urduSoundNote = "فرانسیسی دسترخوان پر کھانا شروع کرنے سے پہلے یہ کہنا لازم ہے۔",
                    audioText = "Bon appétit"
                )
            ),
            questions = listOf(
                LessonQuestion(
                    id = 501,
                    questionEn = "Which pronoun is used for polite/formal 'You' (like Urdu 'آپ')?",
                    questionUr = "فرانسیسی میں باادب 'آپ' کے لیے کون سا لفظ استعمال ہوتا ہے؟",
                    options = listOf("Vous", "Tu", "Je", "Il"),
                    correctAnswerIndex = 0,
                    explanationEn = "'Vous' (وو) is the formal pronoun equivalent to Urdu 'آپ'.",
                    explanationUr = "'Vous' کا مطلب اردو 'آپ' ہے جو باادب گفتگو میں بولا جاتا ہے۔",
                    audioTargetFrench = "Vous"
                )
            )
        ),
        Lesson(
            id = 6,
            titleEn = "Grammar Essentials: Nouns & Verbs",
            titleUr = "گرامر کے بنیادی اصول: اسم اور افعال",
            descriptionEn = "Learn grammatical gender (Le/La), articles, and core verbs Être (To be) & Avoir (To have).",
            descriptionUr = "فرانسیسی اسم کی جنس (مذکر/مؤنث)، حروفِ تعریف اور بنیادی افعال سیکھیں۔",
            iconName = "auto_stories",
            cards = listOf(
                LessonCard(
                    titleEn = "The (Masculine / Feminine)",
                    titleUr = "حرفِ تعریف (Le / La)",
                    frenchPhrase = "Le / La",
                    phoneticEn = "Luh / Lah",
                    phoneticUr = "لو (مذکر) / لا (مؤنث)",
                    meaningEn = "Le (Masculine 'the'), La (Feminine 'the')",
                    meaningUr = "مذکر اسم سے پہلے 'لو' اور مؤنث اسم سے پہلے 'لا' آئے گا",
                    urduSoundNote = "اردو کی طرح فرانسیسی میں بھی ہر چیز مذکر یا مؤنث ہوتی ہے۔",
                    audioText = "Le, La"
                ),
                LessonCard(
                    titleEn = "To Be (Être)",
                    titleUr = "ہونا (ہو / ہے / ہیں)",
                    frenchPhrase = "Je suis / Vous êtes",
                    phoneticEn = "Zhuh swee / Voo zet",
                    phoneticUr = "ژو سوئ / وو زیٹ",
                    meaningEn = "I am / You are",
                    meaningUr = "میں ہوں / آپ ہیں",
                    urduSoundNote = "ژو سوئ = میں ہوں، وو زیٹ = آپ ہیں۔",
                    audioText = "Je suis"
                ),
                LessonCard(
                    titleEn = "To Have (Avoir)",
                    titleUr = "پاس ہونا (ملکیت)",
                    frenchPhrase = "J'ai / Vous avez",
                    phoneticEn = "Zhay / Voo zah-vay",
                    phoneticUr = "ژے / وو زاوے",
                    meaningEn = "I have / You have",
                    meaningUr = "میرے پاس ہے / آپ کے پاس ہے",
                    urduSoundNote = "ژے = میرے پاس ہے (I have).",
                    audioText = "J'ai"
                )
            ),
            questions = listOf(
                LessonQuestion(
                    id = 601,
                    questionEn = "How do you say 'I am' in French?",
                    questionUr = "فرانسیسی میں 'میں ہوں' کیسے کہیں گے؟",
                    options = listOf("Je suis", "J'ai", "Vous êtes", "Tu as"),
                    correctAnswerIndex = 0,
                    explanationEn = "'Je suis' (ژو سوئ) means 'I am'.",
                    explanationUr = "'Je suis' کا مطلب 'میں ہوں' ہے۔",
                    audioTargetFrench = "Je suis"
                )
            )
        )
    )

    val flashcards: List<Flashcard> = listOf(
        // Greetings & Essentials
        Flashcard(1, "Bonjour", "Boh-zhoor", "بونژور (ن٘)", "Hello / Good day", "سلام / صبح بخیر", "Greetings", "سلام اور آداب", "Bonjour, comment allez-vous?", "Hello, how are you?", "سلام، آپ کیسے ہیں؟", isMastered = true, isFavorite = true),
        Flashcard(2, "Merci", "Mair-see", "میرسی", "Thank you", "شکریہ", "Essentials", "ضروری باتیں", "Merci pour votre aide.", "Thank you for your help.", "آپ کی مدد کا شکریہ۔", isMastered = false, isFavorite = true),
        Flashcard(3, "Oui", "Wee", "وی", "Yes", "جی ہاں / ہاں", "Essentials", "ضروری باتیں", "Oui, s'il vous plaît.", "Yes, please.", "جی ہاں، برائے مہربانی۔"),
        Flashcard(4, "Non", "Noh-n", "نوں (نون غنہ)", "No", "جی نہیں / نہیں", "Essentials", "ضروری باتیں", "Non, merci.", "No, thank you.", "جی نہیں، شکریہ۔"),
        Flashcard(5, "Pardon / Excusez-moi", "Pahr-doh / Eks-kyoo-zay mwah", "پارڈوں / ایکسکوزے موا", "Excuse me / Sorry", "معاف کیجیے گا", "Essentials", "ضروری باتیں", "Excusez-moi, où est la gare?", "Excuse me, where is the station?", "معاف کیجیے گا، ریلوے اسٹیشن کہاں ہے؟"),
        Flashcard(6, "Au revoir", "Oh ruh-vwahr", "او رووار / اوغوار", "Goodbye", "خدا حافظ / پھر ملیں گے", "Greetings", "سلام اور آداب", "Au revoir et à bientôt!", "Goodbye and see you soon!", "خدا حافظ اور جلد ملیں گے!"),
        Flashcard(7, "S'il vous plaît", "Seel voo play", "سیل وو پلے", "Please", "برائے مہربانی", "Essentials", "ضروری باتیں", "Un café, s'il vous plaît.", "A coffee, please.", "ایک کافی، برائے مہربانی۔"),

        // Food & Dining
        Flashcard(8, "L'eau", "Loh", "لو", "Water", "پانی", "Food", "کھانا پیتا", "De l'eau, s'il vous plaît.", "Some water, please.", "کچھ پانی، برائے مہربانی۔"),
        Flashcard(9, "Le pain", "Luh pah-n", "لو پاں (نون غنہ)", "Bread", "روٹی / بریڈ", "Food", "کھانا پیتا", "J'aime le pain français.", "I like French bread.", "مجھے فرانسیسی بریڈ پسند ہے۔"),
        Flashcard(16, "Le croissant", "Luh krwah-sah", "لو کرواساں", "Croissant", "فرانسیسی پیسٹری (کروسانٹ)", "Food", "کھانا پیتا", "Un croissant chaud, s'il vous plaît.", "A warm croissant, please.", "ایک گرم کروسانٹ، برائے مہربانی۔"),
        Flashcard(17, "Le fromage", "Luh froh-mahzh", "لو فرماژ", "Cheese", "پنیر / چیز", "Food", "کھانا پیتا", "Le fromage français est délicieux.", "French cheese is delicious.", "فرانسیسی پنیر بہت لذیذ ہے۔"),

        // Travel & Shopping
        Flashcard(10, "Combien ça coûte?", "Koh-byah sah koot?", "کومبیاں سا کوت؟", "How much does it cost?", "یہ کتنے کا ہے؟", "Travel", "سفر اور خریداری", "Bonjour, combien ça coûte?", "Hello, how much is this?", "سلام، یہ کتنے کا ہے؟"),
        Flashcard(13, "L'hôtel", "Loh-tel", "لوٹیل", "Hotel", "ہوٹل", "Travel", "سفر", "Où est l'hôtel?", "Where is the hotel?", "ہوٹل کہاں ہے؟"),
        Flashcard(14, "L'aéroport", "Lah-ay-roh-pohr", "لائروپور (غین کے ساتھ)", "Airport", "ہوائی اڈہ", "Travel", "سفر", "Je vais à l'aéroport.", "I am going to the airport.", "میں ہوائی اڈے جا رہا ہوں۔"),
        Flashcard(18, "Le billet", "Luh bee-yay", "لو بیئے", "Ticket", "ٹکٹ", "Travel", "سفر", "Un billet pour Paris, s'il vous plaît.", "A ticket to Paris, please.", "پیرس کے لیے ایک ٹکٹ، برائے مہربانی۔"),

        // Business & Work
        Flashcard(19, "L'entreprise", "Lah-truh-preez", "لانتریپریز", "Company / Business", "کمپنی / کاروبار", "Business", "کاروبار اور ملازمت", "Je travaille dans une entreprise.", "I work in a company.", "میں ایک کمپنی میں کام کرتا ہوں۔"),
        Flashcard(20, "Le rendez-vous", "Luh rah-day-voo", "لو راندے وو", "Meeting / Appointment", "ملاقات / میٹنگ", "Business", "کاروبار اور ملازمت", "J'ai un rendez-vous à 10h.", "I have a meeting at 10 AM.", "میری صبح 10 بجے میٹنگ ہے۔"),
        Flashcard(21, "Le bureau", "Luh boo-roh", "لو بیورو", "Office", "دفتر / آفس", "Business", "کاروبار اور ملازمت", "Mon bureau est au centre-ville.", "My office is downtown.", "میرا دفتر شہر کے مرکز میں ہے۔"),

        // Culture & Customs
        Flashcard(22, "La Bise", "Lah Beez", "لا بیز", "Cheek kiss greeting", "گال سے گال ملا کر سلام", "Culture", "فرانسیسی تہذیب", "On fait la bise pour se saluer.", "We do cheek kisses to greet.", "ہم سلام کے لیے گال ملاتے ہیں۔"),
        Flashcard(23, "C'est la vie", "Say lah vee", "سے لا وی", "That's life!", "یہ زندگی کا حصہ ہے!", "Culture", "فرانسیسی تہذیب", "C'est dommage, mais c'est la vie!", "Too bad, but that's life!", "افسوس، لیکن یہ زندگی ہے!")
    )

    val conversationScenarios: List<ConversationScenario> = listOf(
        ConversationScenario(
            id = 1,
            titleEn = "Ordering at a Paris Café",
            titleUr = "پیرس کے کافی ہاؤس میں آرڈر",
            situationEn = "You arrive at a cozy Parisian café and want to order a coffee and a croissant.",
            situationUr = "آپ پیرس کی کافی شاپ میں بیٹھے ہیں اور کافی اور کروسانٹ کا آرڈر دینا چاہتے ہیں۔",
            category = "Dining Out",
            dialogueLines = listOf(
                DialogueLine("Waiter", "ویٹر", false, "Bonjour! Que désirez-vous?", "Boh-zhoor! Kuh day-zee-ray voo?", "بونژور! کو دیزیرے وو؟", "Hello! What would you like?", "سلام! آپ کیا لیں گے؟"),
                DialogueLine("You (Practice Speaking)", "آپ (بولنے کی مشق کریں)", true, "Bonjour! Un café et un croissant, s'il vous plaît.", "Boh-zhoor! Uh kah-fay ay uh krwah-sah, seel voo play.", "بونژور! اَں کافی اے اَں کرواساں، سیل وو پلے۔", "Hello! A coffee and a croissant, please.", "سلام! ایک کافی اور ایک کروسانٹ، برائے مہربانی۔", "کرواساں میں آخر میں نون غنہ 'ں' کی آواز نکلتی ہے۔"),
                DialogueLine("Waiter", "ویٹر", false, "Très bien! Autre chose?", "Tray byah! Oh-truh shohz?", "ترے بیاں! اوترو شوز؟", "Very well! Anything else?", "بہت خوب! کچھ اور چاہیے؟"),
                DialogueLine("You (Practice Speaking)", "آپ (بولنے کی مشق کریں)", true, "Non merci, c'est tout.", "Noh mair-see, say too.", "نوں میرسی، سے تو۔", "No thank you, that is all.", "جی نہیں شکریہ، بس اتنا ہی۔")
            )
        ),
        ConversationScenario(
            id = 2,
            titleEn = "Asking Directions to Eiffel Tower",
            titleUr = "ایفل ٹاور کا راستہ پوچھنا",
            situationEn = "You are in central Paris and want to find the Eiffel Tower (La Tour Eiffel).",
            situationUr = "آپ پیرس میں ہیں اور ایفل ٹاور کا راستہ معلوم کرنا چاہتے ہیں۔",
            category = "Travel",
            dialogueLines = listOf(
                DialogueLine("You (Practice Speaking)", "آپ (بولنے کی مشق کریں)", true, "Pardon monsieur, où est la Tour Eiffel?", "Pahr-doh muh-syuh, oo ay lah Toor Ef-fel?", "پارڈوں موسیؤ، او اے لا تور ایفل؟", "Excuse me sir, where is the Eiffel Tower?", "معاف کیجیے گا جناب، ایفل ٹاور کہاں ہے؟"),
                DialogueLine("Passerby", "راستے کا مسافر", false, "C'est tout droit, puis à gauche.", "Say too drwah, pwee ah goh-sh.", "سے تو دروا (غوا)، پوئی آ گوش۔", "It's straight ahead, then to the left.", "یہ بالکل سیدھا آگے ہے، پھر بائیں جانب۔"),
                DialogueLine("You (Practice Speaking)", "آپ (بولنے کی مشق کریں)", true, "Merci beaucoup! Bonne journée!", "Mair-see boh-koo! Bohn zhoor-nay!", "میرسی بوکو! بون ژورنے!", "Thank you very much! Have a good day!", "آپ کا بہت بہت شکریہ! آپ کا دن اچھا گزرے!")
            )
        ),
        ConversationScenario(
            id = 3,
            titleEn = "At the French Bakery (Boulangerie)",
            titleUr = "فرانسیسی بیکری میں خریداری",
            situationEn = "Buying fresh baguettes and croissants at a local Parisian boulangerie.",
            situationUr = "پیرس کی بیکری سے گرم نان (باگیٹ) اور پیسٹری کا آرڈر دینا۔",
            category = "Food & Shopping",
            dialogueLines = listOf(
                DialogueLine("Baker", "بیکر / نانبائی", false, "Bonjour! Je vous écoute?", "Boh-zhoor! Zhuh voo zay-koot?", "بونژور! ژو وو زیکوت؟", "Hello! How can I help you?", "سلام! میں آپ کی کیا خدمت کروں؟"),
                DialogueLine("You (Practice Speaking)", "آپ (بولنے کی مشق کریں)", true, "Une baguette et deux croissants, s'il vous plaît.", "Oon bah-get ay duh krwah-sah, seel voo play.", "یون باگیٹ اے دو کرواساں، سیل وو پلے۔", "One baguette and two croissants, please.", "ایک باگیٹ اور دو کروسانٹ، برائے مہربانی۔"),
                DialogueLine("Baker", "بیکر / نانبائی", false, "Voilà! Ça fera 4 euros 50.", "Vwah-lah! Sah fuh-rah kat-ruh uh-roh sah-kah-t.", "والا! سا فرا 4 یورو 50۔", "Here you go! That will be 4.50 euros.", "یہ لیجیے! 4.50 یورو ہو گئے۔"),
                DialogueLine("You (Practice Speaking)", "آپ (بولنے کی مشق کریں)", true, "Merci, bonne journée!", "Mair-see, bohn zhoor-nay!", "میرسی، بون ژورنے!", "Thank you, have a good day!", "شکریہ، آپ کا دن اچھا گزرے!")
            )
        ),
        ConversationScenario(
            id = 4,
            titleEn = "Buying a Train Ticket at Gare de Lyon",
            titleUr = "ریلوے اسٹیشن پر ٹکٹ خریدنا",
            situationEn = "Reserving a train ticket to Lyon at Paris Gare de Lyon station.",
            situationUr = "پیرس کے ریلوے اسٹیشن پر لیون شہر کے لیے ٹکٹ خریدنا۔",
            category = "Travel",
            dialogueLines = listOf(
                DialogueLine("Agent", "ٹکٹ ایجنٹ", false, "Bonjour! Pour quelle destination?", "Boh-zhoor! Poor kel des-tee-nah-syoh?", "بونژور! پور کیل دیستیناسیوں؟", "Hello! What destination?", "سلام! آپ کس شہر جا رہے ہیں؟"),
                DialogueLine("You (Practice Speaking)", "آپ (بولنے کی مشق کریں)", true, "Un billet aller simple pour Lyon, s'il vous plaît.", "Uh bee-yay ah-lay sah-pluh poor Lee-oh, seel voo play.", "اَں بیئے آلے سانپل پور لیون، سیل وو پلے۔", "One one-way ticket to Lyon, please.", "لیون کا ایک طرف کا ٹکٹ، برائے مہربانی۔"),
                DialogueLine("Agent", "ٹکٹ ایجنٹ", false, "Départ voie 3 à 14h.", "Day-pahr vwah trwah ah kah-tohr-z-ur.", "دیپار ووا تروا آ 14 بجے۔", "Departure platform 3 at 2 PM.", "پلیٹ فارم 3 سے دوپہر 2 بجے روانگی۔")
            )
        ),
        ConversationScenario(
            id = 5,
            titleEn = "Hotel Check-In & Requesting Amenities",
            titleUr = "ہوٹل چیک ان اور خدمات",
            situationEn = "Checking into your hotel in Paris and asking for the Wi-Fi code.",
            situationUr = "پیرس کے ہوٹل میں نائٹ سٹے کے لیے چیک ان اور وائی فائی پاسورڈ مانگنا۔",
            category = "Travel & Hospitality",
            dialogueLines = listOf(
                DialogueLine("Receptionist", "ریسپشنسٹ", false, "Bonjour, bienvenue! Vous avez une réservation?", "Boh-zhoor, bee-yah-vuh-nyoo! Voo zah-vay oon ray-zair-vah-syoh?", "بونژور، بیاں وینیو! وو زاوے یون ریزرواسیوں؟", "Hello, welcome! Do you have a reservation?", "سلام، خوش آمدید! کیا آپ کی بکنگ ہے؟"),
                DialogueLine("You (Practice Speaking)", "آپ (بولنے کی مشق کریں)", true, "Oui, au nom de Khan. Quel est le code Wi-Fi?", "Wee, oh noh duh Khan. Kel ay luh kohd wee-fee?", "وی، او نوم دو خان۔ کیل اے لو کوڈ وائی فائی؟", "Yes, under the name Khan. What is the Wi-Fi code?", "جی ہاں، خان کے نام سے۔ وائی فائی کا کوڈ کیا ہے؟"),
                DialogueLine("Receptionist", "ریسپشنسٹ", false, "Voici votre clé, chambre 204. Le code est 'Paris2026'.", "Vwah-see voh-truh klay, shah-bruh duh sah kat.", "واسی وو ترے کلے، شامبر 204۔", "Here is your key, room 204. The code is 'Paris2026'.", "یہ آپ کی چابی ہے، کمرا نمبر 204۔ پاسورڈ 'Paris2026' ہے۔")
            )
        ),
        ConversationScenario(
            id = 6,
            titleEn = "Business Introduction & Networking",
            titleUr = "بزنس تعارف اور پیشہ ورانہ گفتگو",
            situationEn = "Introducing yourself formally at a business conference in France.",
            situationUr = "فرانسیسی بزنس کانفرنس میں اپنا باادب پیشہ ورانہ تعارف کروانا۔",
            category = "Business",
            dialogueLines = listOf(
                DialogueLine("Colleague", "کاروباری ساتھی", false, "Enchanté! Quel est votre domaine d'activité?", "Ah-shah-tay! Kel ay voh-truh doh-mehn dahk-tee-vee-tay?", "آں شاںتے! کیل اے وو ترے دومین داکتیویتے؟", "Nice to meet you! What is your line of work?", "آپ سے مل کر خوشی ہوئی! آپ کا شعبہ کیا ہے؟"),
                DialogueLine("You (Practice Speaking)", "آپ (بولنے کی مشق کریں)", true, "Je suis consultant en informatique. Voici ma carte.", "Zhuh swee koh-sool-tah ah ah-fohr-mah-teek. Vwah-see mah kahrt.", "ژو سوئ کونسلتوں آں انفارماتیک۔ واسی ما کارت۔", "I am an IT consultant. Here is my card.", "میں آئی ٹی کنسلٹنٹ ہوں۔ یہ میرا وزٹنگ کارڈ ہے۔")
            )
        )
    )
}
