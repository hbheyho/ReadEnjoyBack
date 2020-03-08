/*
Navicat MySQL Data Transfer

Source Server         : HB
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : dbreadenjoy

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-03-08 10:55:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for readenjoy_book
-- ----------------------------
DROP TABLE IF EXISTS `readenjoy_book`;
CREATE TABLE `readenjoy_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书籍Id',
  `book_ISBN` varchar(50) NOT NULL COMMENT '书籍ISBN',
  `category_id` int(11) NOT NULL COMMENT '书籍所属分类ID',
  `book_name` varchar(50) NOT NULL,
  `book_writer` varchar(50) DEFAULT NULL COMMENT '书籍作者',
  `book_transter` varchar(50) DEFAULT NULL COMMENT '译者',
  `book_publish` varchar(60) DEFAULT NULL COMMENT '书籍出版社',
  `book_info` varchar(800) DEFAULT NULL COMMENT '内容简介',
  `book_directory` varchar(800) DEFAULT NULL COMMENT '目录简介',
  `book_writer_information` varchar(800) DEFAULT NULL COMMENT '作者简介',
  `book_image` varchar(250) DEFAULT NULL COMMENT '书籍图片',
  `book_score` double(11,2) DEFAULT NULL COMMENT '书籍评分',
  `book_status` int(6) DEFAULT '1' COMMENT '书籍状态1-存在 2-下架 3-删除',
  `book_down_number` int(50) DEFAULT '0' COMMENT '书籍总下载数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`book_id`),
  KEY `book_ISBN` (`book_ISBN`),
  KEY `bookcategory` (`category_id`),
  CONSTRAINT `bookcategory` FOREIGN KEY (`category_id`) REFERENCES `readenjoy_bookcategory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readenjoy_book
-- ----------------------------
INSERT INTO `readenjoy_book` VALUES ('2', '9787020028115', '200014', '骆驼祥子', '老舍 ', '', '人民文学出版社', '《骆驼祥子》是老舍用同情的笔触描绘的一幕悲剧：二十年代的北京，一个勤劳、壮实的底层社会小人物怀着发家、奋斗的美好梦想，却最终为黑暗的暴风雨所吞噬。它揭示了当时“小人物”的奴隶心理和希望的最终破灭。随着祥子心爱的女人小福子的自杀，祥子熄灭了个人奋斗的最后一朵火花。这是旧中国老北京贫苦市民的典型命运。', '暂无', '老舍（1899―1966），现代著名作家。出身北京贫民家庭，1924年赴英国教书，1930年回国后长期在青岛大学等校任教授。擅长表现北京下层人民的贫苦生活及其悲惨命运。主要作品有《骆驼祥子》、《四世同堂》、《月牙儿》等小说。解放后热情歌颂新社会，创作了《龙须沟》、《茶馆》等二十多个剧本以及大量的小说、散文、荣获“人民艺术家”称号。曾任全国人大代表、中国文联副主席等职。', '骆驼祥子.jpg', '8.30', '0', '1000', '2018-06-16 16:20:30', '2018-06-16 16:20:33');
INSERT INTO `readenjoy_book` VALUES ('4', '9787020036509', '200014', '茶馆', '老舍', '', '人民文学出版社', '三幕话剧《茶馆》以北京一家大茶馆为背景，描写了清末、民初、抗战胜利以后三个历史时期的北京生活风貌，深刻地写出了王利发等人的真实命运。', '暂无', '老舍（1899―1966），现代著名作家。出身北京贫民家庭，1924年赴英国教书，1930年回国后长期在青岛大学等校任教授。擅长表现北京下层人民的贫苦生活及其悲惨命运。主要作品有《骆驼祥子》、《四世同堂》、《月牙儿》等小说。解放后热情歌颂新社会，创作了《龙须沟》、《茶馆》等二十多个剧本以及大量的小说、散文、荣获“人民艺术家”称号。曾任全国人大代表、中国文联副主席等职。', '茶馆.jpg', '9.00', '1', '1000', '2018-06-16 16:24:43', '2018-06-16 16:24:46');
INSERT INTO `readenjoy_book` VALUES ('5', '9787020002207', '200011', '红楼梦', '曹雪芹 著 / 高鹗 续 ', '', '人民文学出版社', '《红楼梦》是一部百科全书式的长篇小说。以宝黛爱情悲剧为主线，以四大家族的荣辱兴衰为背景，描绘出18世纪中国封建社会的方方面面，以及封建专制下新兴资本主义民主思想的萌动。结构宏大、情节委婉、细节精致，人物形象栩栩如生，声口毕现，堪称中国古代小说中的经 典。\r\n\r\n由红楼梦研究所校注、人民文学出版社出版的《红楼梦》以庚辰（1760）本《脂砚斋重评石头记》为底本，以甲戌（1754）本、已卯（1759）本、蒙古王府本、戚蓼生序本、舒元炜序本、郑振铎藏本、红楼梦稿本、列宁格勒藏本（俄藏本）、程甲本、程乙本等众多版本为参校本，是一个博采众长、非常适合大众阅读的本子；同时，对底本的重要修改，皆出校记，读者可因以了解《红楼梦》的不同版本状况。', '暂无', '曹雪芹,（？-1763，一作1764）清小说家。名霑，字梦阮，号雪芹、芹圃、芹溪。为满洲正白旗“包衣”人。自曾祖起，三代任江宁织造，其祖曹寅尤为康熙帝所信用。雍正初年，在统计阶级内部政治斗争牵连下，雪芹家受到重大打击，其父免职，产业被抄，遂随家迁居北京。他早年经历了一段封建大官僚地主家庭的繁华生活，后因家道衰落，趋于艰困。晚期居北京西郊，贫病而卒，年未及五十。性情高傲，嗜酒健谈。具有深厚的文化修养和卓越的艺术才能。他生活在我国已有资本主义生产萌芽的封建末世，在其后期又有机会接触到下层人民，因而对当时社会阶级斗争和思想斗争有较具体的感受，看到了统治阶级的腐朽凶残和内部的分崩离析。曾以十年时间，从事《石头记》（即《红楼梦》）的创作。书中通过一个贵族官僚大家庭的盛衰历史的描写，塑造了许多典型人物形象，对当时社会的黑暗腐败，进行了深刻的解剖和批判，并热情地歌颂了具有异端思想的男女青年，成为我国古典小说中伟大的现实主义作品。但其中也反映了作者为封建制度“补天”的幻想和找不到出路的悲观情绪。据称先后曾增删五次，但未成全书而卒；今流行本一百二十回，后四十回一般认为是高鹗所续。也能诗，又善画石，但作品流传绝少。', '红楼梦.jpg', '8.80', '1', '1000', '2018-06-16 16:26:24', '2018-06-16 16:26:27');
INSERT INTO `readenjoy_book` VALUES ('6', '9787805200552', '200011', '西游记', '吴承恩', '', '岳麓书社', '《西游记》的艺术虚构正是建立在传统艺术经验和这种社会的宗教性观念和风习的基础之上的，但它又以作者融会了传统艺术经验所形成的艺术的独创性批判了社会的宗教性观念，或更正确地说，和社会的宗教性观念开了玩笑，进行了潮弄。这是这部演述超人间故事的神魔小说最突出、最优异的品质，也是它的艺术价值和魅力的最根本的所在。\r\n\r\n《西游记》的主旨就是在于政治批判，那就和小说的形象和意蕴不符了。对现实政治的讽刺顶多只是这部神魔小说的附带性的内容，是信手拈来之笔，是讽喻世态人情中的涉笔成趣。一部小说如果不包含丰富的、多元的、在众多棱面上映射出众多意象的内涵，就不成其为多姿多采的有生命力的艺术品，不会万古长新。', '暂无', '吴承恩(1501年-1582年)，汉族。字汝忠，号射阳山人，淮安府山阳县（今江苏省淮安市楚州区）人,明代小说家。吴承恩大约40岁才补得一个岁贡生，到北京等待分配官职，没有被选上，由于母老家贫，去做了长兴县丞，终因受人诬告，两年后“拂袖而归”，晚年以卖文为生，大约活了82岁。\r\n\r\n《天启淮安府志》评价他“性敏而多慧，博极群书，为诗文下笔立成，清雅流丽，有秦少游之风。复善谐谑，所著杂记几种，名震一时”。不过都是他死后的事了。他一生创作丰富，但是由于家贫，又没有子女，作品多散失。据记载有志怪小说集《禹鼎记》已失传。目前只遗留后人辑的《射阳先生存稿》四卷。一般公认他是中国的《西游记》的最后定稿作者，但也有观点认为不是，目前在学术界保持着争议(旧时传说是元朝的全真教道人丘处机)。', '西游记.jpg', '8.60', '1', '1000', '2018-06-16 16:28:33', '2018-06-16 16:28:37');
INSERT INTO `readenjoy_book` VALUES ('7', '9789810553067', '200011', '金瓶梅', '笑笑生', null, '新加坡南洋出版社', '这套《金瓶梅》（上、下册，约1240印刷页）是全球第一部简体、双版本、带200原图（其中30多幅春宫图也全部保留）、不做任何删节处理的版本。是目前海内外各种版本中内容最全的版本。\r\n\r\n它以北京大学图书馆藏善本影印本为蓝本制作的。包括拥有最充分原始信息、最具可读性的“崇祯本”全部内容（该版本曾面向中国高级领导干部、学者和大型图书馆少量印制），并附录万历“词话本”中和“崇祯本”内容不同的部分（将“词话本”中与“崇祯本”差别较大的第一回前半部分，第五十三、五十四回，以及词话本特有，而崇祯本所无的“欣欣自序”、“廿公跋”、“词曰”以及“四贪词”，也一并附上）使得读者花一套书的钱，同时可得两大主要版本的内容。', '暂无', '兰陵笑笑生是第一奇书《金瓶梅》的作者。此人真实身份已成为历史谜团。《金瓶梅》廿公跋说《金瓶梅传》，为世庙时一巨公寓言。明沈德符《万历野获编》则说他是嘉靖间大名士手笔。作为中国文学史上第一位独立创作长篇小说的作家，兰陵笑笑生在小说创作上达到了前所未有的高度，他所创作的《金瓶梅》以市井人物与世俗风情为描写中心，开启了文人直接取材于现实社会生活而创作长篇小说的先河。兰陵即今山东省临沂市苍山县，且书中大量运用山东口语，作者有可能是山东人，或通晓山东环境。兰陵笑笑生真实作者之考证，至今犹为一大难题，被喻为中国文学古代作家考证界的“哥德巴赫猜想”。', '金瓶梅.jpg', '9.00', '1', '1000', '2018-06-16 16:30:40', '2018-06-16 16:30:42');
INSERT INTO `readenjoy_book` VALUES ('8', '9787805985282', '200007', '诗经', '孔子', '清如许 / 王洁 ', '山西古籍出版社', '《诗经》在中国乃至世界文化史上都占有重要地位。它描写现实、反映现实的写作手法，开创了诗歌创作的现实主义优良传统，历代诗人的诗歌创作不同程序地受到《诗经》的影响。《诗经》曾被译为多国文字，日本、朝鲜、越南、法国、德国、英国、俄国都有译本，流传非常广泛。作为创造民族新文化的基石，我们一定要很好地继承这一光辉灿烂的文化遗产。《诗经》是我国第一部诗歌总集，共收入自西周初年至春秋中叶大约五百多年的诗歌三百零五篇。《诗经》共分风、雅、颂三大部分，本书对诗经的诗篇进行了翻译和解说，并探讨了诗歌的文化内涵，是一本很好的研读《诗经》的工具书。', '暂无', '暂无', '诗经.jpg', '7.60', '1', '1000', '2018-06-16 16:32:25', '2018-06-16 16:32:27');
INSERT INTO `readenjoy_book` VALUES ('9', '9787538270013', '200021', '傅雷家书', '傅雷', null, '辽宁教育出版社', '傅雷夫妇作为中国父母的典范，一生苦心孤诣，呕心沥血培养的两个孩子，傅聪——著名钢琴大师；傅敏——英语特级教师。是他们先做人、后成“家”，超脱小我，独立思考，因材施教等教育思想的成功体现。家书中父母的谆谆教诲，孩子与父母的真诚交流，亲情溢于字里行间，给天下父母子女强烈的感 染启迪。\r\n\r\n《傅雷家书》不是普通的家书。傅雷在给傅聪的信里这样说：“长篇累牍的给你写信，不是空唠叨，不是莫名其妙的GOSSIP，而是有好几种作用。第一，我的确把你当做一个讨论艺术，讨论音乐的对手，第二，极想激出你一些青年人的感想，让我做父亲的得些新鲜养料，同时也可以间接传布给别的青年，第三，借通信训练你的－不但是文笔，而尤其是你的思想，第四，我想时时刻刻，随处给你做个警钟，做面‘忠实的镜子’，不论在做人方面，在生活细节方面，在艺术修养方面，在演奏姿态方面。”贯穿全部家书的情意，是要儿子知道国家的荣辱，艺术的尊严，能够用严肃的态度对待一切，做一个“德艺俱备，人格卓越的艺术家”。\r\n\r\n', '暂无', '傅雷（1908-1966），我国著名文学艺术翻译家，从三是年代起，即致力于法国文学的翻译介绍工作，毕生翻译作品三十余部，主要有罗曼·罗兰长篇巨著《约翰·克里斯朵夫》、传记《贝多芬传》、《托尔斯泰传》、《弥盖朗琪罗传》，巴尔扎克名著《高老头》、《欧也妮·葛朗台》、《贝姨》、《邦斯舅舅》、《亚尔培·萨伐龙》、《夏倍上校》、《搅水女人》、《都尔的本堂神甫》、《幻灭》、《赛查·皮罗多盛衰记》、《于恕尔·弥罗埃》，伏尔泰的《老实人》、《天真汉》、《查第格》，梅里美的《嘉尔曼》、《高龙巴》，丹纳名著《艺术哲学》等。写有《世界美术名作二十讲》专著，以及《贝多芬的作品及其精神》等论文', '傅雷家书.jpg', '8.80', '1', '1000', '2018-06-16 16:34:24', '2018-06-16 16:34:27');
INSERT INTO `readenjoy_book` VALUES ('11', '0000000000001', '200023', '黑猫', 'エドガー·アラン·ポー', '佐々木 直次郎', '豆瓣阅读', '酒癖の悪い主人公が、可愛がっていた黒猫の片目をナイフでえぐり取り、しまいには木に吊るして殺してしまうが・・・過度の飲酒によって精神が荒廃していく様を黒猫をモチーフに描いたサスペンス小説の傑作です。\r\n\r\n酒品很差的主人公，将可爱的黑猫的一只眼睛用刀挖出来，之后还把它吊死在树上……由于过度饮酒导致的精神荒废，是一部以黑猫为切入点的杰出的悬疑推理小说。', '暂无', '暂无', '黑猫.jpg', '8.60', '1', '1000', '2018-06-16 16:36:14', '2018-06-16 16:36:18');
INSERT INTO `readenjoy_book` VALUES ('38', '0000000000002', '200022', 'The Count of Monte Cristo（基督山伯爵）', 'Alexandre Dumas', '', '豆瓣阅读', 'The story takes place in France, Italy, islands in the Mediterranean and the Levant during the historical events of 1815–1838 (from just before the Hundred Days through the reign of Louis-Philippe of France). The historical setting is a fundamental element of the book. It is primarily concerned with themes of hope, justice, vengeance, mercy, forgiveness and death, and is told in the style of an adventure story.', null, '大仲马：19世纪法国浪漫主义作家。大仲马自学成才，一生写的各种著作达300卷之多，主要以小说和剧作著称于世。他最著名的作品包括《基督山伯爵》、《三剑客》、《二十年后》和《布拉热洛纳子爵》，后三部通称为达尔达尼央浪漫三部曲。', '基督山伯爵.jpg', '8.70', '1', '1000', '2018-06-18 22:36:58', '2018-06-18 22:36:58');
INSERT INTO `readenjoy_book` VALUES ('39', '9787506318983', '200014', '郁达夫 ', '达夫 ', '', '作家出版社', '《沉沦》于1921年10月由上海泰东书局出版，是郁达夫的第一部小说集，在《沉沦》里作者完全无视社会道德观念，大胆地写出被情欲折磨的青年的心灵，短篇小说《沉沦》是这部小集中最可注意的一篇。写的是一个在日本留学的中国人爱上了一位日本的少女，却因为积弱的民族造成的自卑而不敢表白，最后只有自杀。集中作品还大胆地描写了偷窥、手淫等个人隐秘行为，在一个道德至上的社会里，这是完全不能容忍的。所以这部小集出版之后，立刻遭到上海文艺界最猛烈的攻击，同时也显示了人本的意义。', null, '郁达夫（1896—1945），原名郁文，幼名荫生、阿凤，字达夫，浙江富阳人，中国现代著名小说家、散文家、诗人。郁达夫精通五门外语，分别为日语、英语、德语、法语、马来西亚语。1921年6月，郁达夫和郭沫若、成仿吾等人组织成立创造社，担任《创造季刊》、《创造月刊》、《洪水》半月刊编辑，同年10月，出版我国现代文学史上第一部白话短篇小说集《沉沦》。由此奠定了郁达夫在新文学运动中的重要地位。代表作《故都的秋》、《春风沉醉的晚上》、《过去》、《迟桂花》等。曾经与徐志摩作为同班同学。', '沉沦.jpg', '9.50', '1', '1000', '2018-06-18 23:06:33', '2018-06-18 23:06:33');
INSERT INTO `readenjoy_book` VALUES ('40', '9787020008742', '200011', '水浒传', '施耐庵 ', '', '人民文学出版社', '《水浒传》是我国第一部以农民起义为题材的长篇章回小说，是我国文学史上一座巍然屹立的丰碑，也是世界文学宝库中一颗光彩夺目的明珠。数百年来，它一直深受我国人民的喜爱，并被译为多种文字，成为我国流传最为广泛的古代长篇小说之一。\r\n\r\n《水浒传》具体而生动地描写了以宋江为首的农民起义发生、发展直至失财的整个过程，揭露了封建社会的黑暗、腐朽和统治阶级的种种罪恶，热情歌颂了起义英雄的反抗精神和正义行动，塑造了一大批梁山好汉的光辉形象，形象地揭示了封建社会“官逼民反”的客观真理和农民起义失败的内在原因。', null, '　施耐庵，（1296—1370），原名彦端，字肇瑞，号子安，别号耐庵。今江苏兴化人。元末明初的文学家，本名彦端，汉族，祖籍是泰州海陵县或苏州吴县阊门（今江苏苏州）。博古通今，才气横溢，举凡群经诸子，词章诗歌，天文、地理、医卜、星象等，一切技术无不精通，35岁曾中进士，后弃官归里，闭门着述，与门下弟子罗贯中一起研究《三国演义》《三遂平妖传》的创作，搜集整理关于梁山泊、宋江等英雄人物的故事，最终写成“四大名著”之一的《水浒传》。施耐庵于元延祐元年（1314年）中秀才，泰定元年（1324年）中举人，至顺二年（1331年）登进士不久任浙江钱塘县尹。施耐庵故里江苏兴化新垛乡施家桥村有墓园、纪念馆，有《施氏家薄谱》存世。', '水浒传.jpg', '8.80', '1', '1000', '2018-06-18 23:13:44', '2018-06-18 23:13:44');
INSERT INTO `readenjoy_book` VALUES ('41', '9787020008728', '200011', '三国演义', '罗贯中', '', '人民文学出版社', '《三国演义》又名《三国志演义》、《三国志通俗演义》，是我国小说史上最著名最杰出的长篇章回体历史小说。 《三国演义》的作者是元末明初人罗贯中，由毛纶，毛宗岗父子批改。在其成书前，“三国故事”已经历了数百年的历史发展过程。在唐代，三国故事已广为流传，连儿童都很熟悉。随着市民文艺的发展，宋代的“说话”艺人，已有专门说三国故事的，当时称为“说三分”。元代出现的《三国志平话》，实际上是从说书人使用的本子，虽较简略粗糙，但已初肯《三国演义》的规模。罗贯中在群众传说和民间艺人创作的基础上，又依据陈寿《三国志》及裴松之注中所征引的资料（还包括《世说新语》及注中的资料），经过巨大的创作劳动，写在了规模宏伟的巨著——《三国志通俗演义》全书24卷，240回。后来经过毛纶，毛宗岗父子批改，形成我们现在所见的《三国演义》120回版', null, '罗贯中（约1330—约1400），汉族，名本，字贯中，号湖海散人。山西太原人，一说钱塘（现在浙江杭州）或庐陵（现在江西吉安）人。元末明初著名小说家、戏曲家，是中国章回小说的鼻祖。一生著作颇丰，主要作品有：剧本《赵太祖龙虎风云会》《忠正孝子连环谏》、《三平章死哭蜚虎子》；小说《隋唐两朝志传》《残唐五代史演义》《三遂平妖传》《粉妆楼》、和施耐庵合著的《水浒传》、代表作《三国演义》等。', '三国演义.jpg', '0.00', '1', '1000', '2018-06-18 23:15:38', '2018-06-18 23:15:38');

-- ----------------------------
-- Table structure for readenjoy_bookcategory
-- ----------------------------
DROP TABLE IF EXISTS `readenjoy_bookcategory`;
CREATE TABLE `readenjoy_bookcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(1) DEFAULT NULL COMMENT '类别状态1-正常,2-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `creat_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200041 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readenjoy_bookcategory
-- ----------------------------
INSERT INTO `readenjoy_bookcategory` VALUES ('200001', '0', '国学', '1', null, '2018-05-29 09:13:30', '2018-05-29 09:13:34');
INSERT INTO `readenjoy_bookcategory` VALUES ('200002', '0', '小说', '1', null, '2018-05-29 09:13:46', '2018-05-29 09:13:49');
INSERT INTO `readenjoy_bookcategory` VALUES ('200003', '0', '文化', '1', null, '2018-05-29 09:14:00', '2018-05-29 09:14:03');
INSERT INTO `readenjoy_bookcategory` VALUES ('200004', '0', '生活', '1', null, '2018-05-29 09:14:19', '2018-05-29 09:14:22');
INSERT INTO `readenjoy_bookcategory` VALUES ('200006', '0', '外国文学', '1', null, '2018-05-29 09:15:07', '2018-05-29 09:15:11');
INSERT INTO `readenjoy_bookcategory` VALUES ('200007', '200001', '经类', '1', null, '2018-05-29 09:15:35', '2018-05-29 09:15:37');
INSERT INTO `readenjoy_bookcategory` VALUES ('200008', '200001', '史类', '1', null, '2018-05-29 09:15:53', '2018-05-29 09:15:55');
INSERT INTO `readenjoy_bookcategory` VALUES ('200009', '200001', '子类', '1', null, '2018-05-29 09:16:32', '2018-05-29 09:16:36');
INSERT INTO `readenjoy_bookcategory` VALUES ('200010', '200001', '集类', '1', null, '2018-05-29 09:16:53', '2018-05-29 09:16:57');
INSERT INTO `readenjoy_bookcategory` VALUES ('200011', '200002', '经典名著', '1', null, '2018-05-29 09:17:11', '2018-05-29 09:17:14');
INSERT INTO `readenjoy_bookcategory` VALUES ('200012', '200002', '近代文学', '1', null, '2018-05-29 09:17:38', '2018-05-29 09:17:40');
INSERT INTO `readenjoy_bookcategory` VALUES ('200013', '200002', '现代文学', '1', null, '2018-05-29 09:17:55', '2018-05-29 09:17:57');
INSERT INTO `readenjoy_bookcategory` VALUES ('200014', '200002', '当代文学', '1', null, '2018-05-29 09:18:14', '2018-05-29 09:18:17');
INSERT INTO `readenjoy_bookcategory` VALUES ('200015', '200003', '散文', '1', null, '2019-02-03 13:55:22', '2019-02-03 13:55:24');
INSERT INTO `readenjoy_bookcategory` VALUES ('200016', '200003', '诗集', '1', null, '2019-02-03 13:55:47', '2019-02-03 13:55:49');
INSERT INTO `readenjoy_bookcategory` VALUES ('200017', '200003', '哲学', '1', null, '2019-02-03 13:56:05', '2019-02-03 13:56:07');
INSERT INTO `readenjoy_bookcategory` VALUES ('200018', '200003', '传记', '1', null, '2019-02-03 13:56:47', '2019-02-03 13:56:49');
INSERT INTO `readenjoy_bookcategory` VALUES ('200019', '200003', '艺术', '1', null, '2019-02-03 13:57:12', '2019-02-03 13:57:16');
INSERT INTO `readenjoy_bookcategory` VALUES ('200020', '200003', '历史', '1', null, '2019-02-03 13:57:46', '2019-02-03 13:57:49');
INSERT INTO `readenjoy_bookcategory` VALUES ('200021', '200004', '教育', '1', null, '2019-02-03 14:00:25', '2019-02-03 14:00:26');
INSERT INTO `readenjoy_bookcategory` VALUES ('200022', '200006', '北美文学', '1', null, '2019-02-03 14:02:22', '2019-02-03 14:02:23');
INSERT INTO `readenjoy_bookcategory` VALUES ('200023', '200006', '日本文学', '1', null, '2019-02-03 14:02:35', '2019-02-03 14:02:36');
INSERT INTO `readenjoy_bookcategory` VALUES ('200024', '200006', '欧洲文学', '1', null, '2019-02-03 14:02:52', '2019-02-03 14:02:54');
INSERT INTO `readenjoy_bookcategory` VALUES ('200025', '200006', '其他', '1', null, '2019-02-03 14:03:12', '2019-02-03 14:03:14');
INSERT INTO `readenjoy_bookcategory` VALUES ('200040', '200004', '游记', '1', null, '2019-02-18 12:38:37', '2019-02-18 12:38:40');

-- ----------------------------
-- Table structure for readenjoy_bookversion
-- ----------------------------
DROP TABLE IF EXISTS `readenjoy_bookversion`;
CREATE TABLE `readenjoy_bookversion` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '版本ID',
  `upload_user` varchar(50) DEFAULT NULL COMMENT '上传用户名',
  `book_ISBN` varchar(50) NOT NULL COMMENT '书籍ISBN',
  `book_size` varchar(20) DEFAULT NULL COMMENT '书籍大小',
  `book_originName` varchar(200) DEFAULT NULL COMMENT '书籍原始名',
  `book_uploadName` varchar(200) DEFAULT NULL COMMENT '书籍上传名',
  `down_number` int(50) DEFAULT '0' COMMENT '版本下载量',
  `collect_number` int(50) DEFAULT '0' COMMENT '版本收藏量',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `forekeybookversion` (`book_ISBN`),
  KEY `forejeyusername` (`upload_user`),
  CONSTRAINT `forejeyusername` FOREIGN KEY (`upload_user`) REFERENCES `readenjoy_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `forekeybookversion` FOREIGN KEY (`book_ISBN`) REFERENCES `readenjoy_book` (`book_ISBN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readenjoy_bookversion
-- ----------------------------
INSERT INTO `readenjoy_bookversion` VALUES ('75', 'HBHB', '9787020008742', '20.0KB', '指导老师、评阅老师评价(双面打印).docx', '5b75b9c7-39c7-4178-ace0-aeb63d587eba.docx', '0', '2', '2019-01-26 13:14:02', '2019-01-26 13:14:02');
INSERT INTO `readenjoy_bookversion` VALUES ('76', 'HBHB', '9787020008742', '20.0KB', '指导老师、评阅老师评价(双面打印).docx', '515d1193-181b-4ad8-84b4-22e884521694.docx', '0', '0', '2019-01-26 13:14:45', '2019-01-26 13:14:45');
INSERT INTO `readenjoy_bookversion` VALUES ('77', 'HBHB', '9787020008742', '20.0KB', '指导老师、评阅老师评价(双面打印).docx', '61b9d396-25f2-4790-b218-457b8635fb5b.docx', '1', '2', '2019-01-26 13:18:07', '2019-01-26 13:18:07');
INSERT INTO `readenjoy_bookversion` VALUES ('78', 'HBHB', '9787020008742', '320.0KB', '前序和任务书（双面打印）.pdf', '797fb01d-f264-4c12-82f0-2e77f84934b2.pdf', '0', '0', '2019-01-27 15:58:12', '2019-01-27 15:58:12');
INSERT INTO `readenjoy_bookversion` VALUES ('79', 'HBHB', '9787020028115', '320.0KB', '前序和任务书（双面打印）.pdf', 'eccb3d8f-7e9d-4731-a51b-ce9ffa0107af.pdf', '0', '1', '2019-01-27 17:02:53', '2019-01-27 17:02:53');
INSERT INTO `readenjoy_bookversion` VALUES ('80', 'HBHB', '9787020028115', '320.0KB', '前序和任务书（双面打印）.pdf', 'c755bb56-67c3-4a30-8a73-8f91248b8139.pdf', '2', '1', '2019-01-27 17:19:48', '2019-01-27 17:19:48');
INSERT INTO `readenjoy_bookversion` VALUES ('81', 'HBHB', '9787805200552', '320.0KB', '前序和任务书（双面打印）.pdf', '1709dcc9-4dc9-403f-80f7-2d372e39236b.pdf', '0', '1', '2019-01-27 17:26:40', '2019-01-27 17:26:40');
INSERT INTO `readenjoy_bookversion` VALUES ('82', 'HBHB', '9787020036509', '1.81M', '复试流程.pdf', '534b943a-ca62-4c3b-89b5-496020038ae8.pdf', '1', '1', '2019-02-18 16:58:55', '2019-02-18 16:58:55');
INSERT INTO `readenjoy_bookversion` VALUES ('83', '201526706037', '9789810553067', '201.0KB', '准考证.pdf', '6eb1dab0-f7d6-46c3-ba3f-865b55d46895.pdf', '0', '0', '2019-02-22 15:48:13', '2019-02-22 15:48:13');
INSERT INTO `readenjoy_bookversion` VALUES ('84', 'HBHB', '9787805200552', '264.0KB', '本科成绩—黄斌.pdf', 'f4f446e3-c936-4373-9e8a-0b8e235f975c.pdf', '0', '0', '2019-02-26 22:51:52', '2019-02-26 22:51:52');
INSERT INTO `readenjoy_bookversion` VALUES ('85', 'HBHB', '9787020002207', '201.0KB', '准考证.pdf', '11d95187-1687-4ed1-8751-f9f5008d4f0d.pdf', '0', '1', '2019-03-03 19:31:14', '2019-03-03 19:31:14');
INSERT INTO `readenjoy_bookversion` VALUES ('86', 'HBHB', '9787020002207', '2.93M', '红楼梦脂批本完美版pdf版.pdf', '0a9445de-6a8e-43ad-b22d-35b8ed7a9341.pdf', '6', '1', '2019-04-05 09:33:22', '2019-04-05 09:33:22');

-- ----------------------------
-- Table structure for readenjoy_comments
-- ----------------------------
DROP TABLE IF EXISTS `readenjoy_comments`;
CREATE TABLE `readenjoy_comments` (
  `CId` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `book_version` int(11) DEFAULT NULL COMMENT '所评论书籍版本',
  `book_ISBN` varchar(50) DEFAULT NULL COMMENT '所评论书籍ISBN',
  `comment_info` varchar(250) DEFAULT NULL COMMENT '评论信息',
  `comment_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论书籍',
  `comment_mark` varchar(50) DEFAULT NULL COMMENT '评论备注信息',
  PRIMARY KEY (`CId`),
  KEY `user_email` (`user_email`),
  KEY `book_ISBN` (`book_ISBN`),
  KEY `book_version` (`book_version`),
  CONSTRAINT `book_ISBN` FOREIGN KEY (`book_ISBN`) REFERENCES `readenjoy_book` (`book_ISBN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `book_version` FOREIGN KEY (`book_version`) REFERENCES `readenjoy_bookversion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_email` FOREIGN KEY (`user_email`) REFERENCES `readenjoy_user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readenjoy_comments
-- ----------------------------
INSERT INTO `readenjoy_comments` VALUES ('53', '442340324@qq.com', '78', '9787020002207', '9091111', '2019-03-10 17:32:08', '');
INSERT INTO `readenjoy_comments` VALUES ('54', '442340324@qq.com', '83', '9787020008742', '909', '2019-03-09 21:24:55', '');
INSERT INTO `readenjoy_comments` VALUES ('55', '442340324@qq.com', '78', '9787020008742', '909', '2019-01-27 16:00:44', '');
INSERT INTO `readenjoy_comments` VALUES ('56', '442340324@qq.com', '78', '9787020008742', '909', '2019-01-27 16:00:44', '');
INSERT INTO `readenjoy_comments` VALUES ('57', '442340324@qq.com', '78', '9787020008742', '909', '2019-01-27 16:00:44', '');
INSERT INTO `readenjoy_comments` VALUES ('58', '442340324@qq.com', '78', '9787020008742', '909', '2019-01-27 16:00:44', '');
INSERT INTO `readenjoy_comments` VALUES ('59', '442340324@qq.com', '78', '9787020008742', '909', '2019-01-27 16:00:44', '');
INSERT INTO `readenjoy_comments` VALUES ('60', '442340324@qq.com', '78', '9787020008742', '909', '2019-01-27 16:00:44', '');
INSERT INTO `readenjoy_comments` VALUES ('61', '442340324@qq.com', '78', '9787020008742', '909', '2019-01-27 16:00:44', '');
INSERT INTO `readenjoy_comments` VALUES ('62', '442340324@qq.com', '78', '9787020008742', '909', '2019-01-27 16:00:44', '');
INSERT INTO `readenjoy_comments` VALUES ('63', '442340324@qq.com', '78', '9787020008742', '909', '2019-01-27 16:00:44', '');
INSERT INTO `readenjoy_comments` VALUES ('64', '442340324@qq.com', '78', '9787020008742', '909', '2019-01-27 16:00:44', '');
INSERT INTO `readenjoy_comments` VALUES ('65', '442340324@qq.com', '78', '9787020008742', '909', '2019-01-27 16:00:44', '');

-- ----------------------------
-- Table structure for readenjoy_feedback
-- ----------------------------
DROP TABLE IF EXISTS `readenjoy_feedback`;
CREATE TABLE `readenjoy_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fb_name` varchar(25) DEFAULT NULL COMMENT '反馈名称',
  `fb_info` varchar(250) DEFAULT NULL COMMENT '反馈内容',
  `fb_status` int(11) DEFAULT NULL COMMENT '反馈状态---0-未处理 1-已处理',
  `fb_usrname` varchar(50) DEFAULT NULL COMMENT '反馈',
  `fb_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_username` (`fb_usrname`),
  CONSTRAINT `fk_username` FOREIGN KEY (`fb_usrname`) REFERENCES `readenjoy_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readenjoy_feedback
-- ----------------------------
INSERT INTO `readenjoy_feedback` VALUES ('3', '书籍反馈', '网站打开不', '1', 'HBHB', '2019-02-26 21:58:28');
INSERT INTO `readenjoy_feedback` VALUES ('8', '网站反馈', 'hahahhaha', '0', 'HBHB', '2019-02-26 22:47:58');
INSERT INTO `readenjoy_feedback` VALUES ('9', '书籍反馈', '213123', '1', 'HBHB', '2019-02-26 22:48:44');
INSERT INTO `readenjoy_feedback` VALUES ('10', '网站反馈', '11', '0', 'HBHB', '2019-02-26 22:49:39');
INSERT INTO `readenjoy_feedback` VALUES ('11', '书籍反馈', '111', '1', 'HBHB', '2019-02-26 22:49:46');
INSERT INTO `readenjoy_feedback` VALUES ('12', '书籍反馈', '11111111', '1', 'HBHB', '2019-02-26 22:49:53');
INSERT INTO `readenjoy_feedback` VALUES ('13', '网站反馈', '111111111111111', '1', 'HBHB', '2019-03-10 19:41:28');
INSERT INTO `readenjoy_feedback` VALUES ('14', '书籍反馈', '123123123', '1', 'HBHB', '2019-02-26 22:50:13');
INSERT INTO `readenjoy_feedback` VALUES ('15', '网站反馈', '12312312312312', '0', 'HBHB', '2019-02-26 22:50:22');
INSERT INTO `readenjoy_feedback` VALUES ('16', '网站反馈', '11', '0', 'HBHB', '2019-02-26 22:51:12');
INSERT INTO `readenjoy_feedback` VALUES ('17', '网站反馈', '11', '0', 'HBHB', '2019-02-26 22:52:41');
INSERT INTO `readenjoy_feedback` VALUES ('18', '网站反馈', '555555555555', '0', 'HBHB', '2019-02-26 22:54:18');
INSERT INTO `readenjoy_feedback` VALUES ('19', '网站反馈', '111', '0', 'HBHB', '2019-02-26 22:56:41');
INSERT INTO `readenjoy_feedback` VALUES ('20', '书籍反馈', '111', '1', 'HBHB', '2019-02-26 22:57:12');
INSERT INTO `readenjoy_feedback` VALUES ('21', '网站反馈', '33', '0', 'HBHB', '2019-03-03 10:05:05');
INSERT INTO `readenjoy_feedback` VALUES ('22', '网站反馈', '垃圾网站', '0', '201526706037', '2019-03-10 19:16:52');
INSERT INTO `readenjoy_feedback` VALUES ('23', '网站反馈', '垃圾网站', '0', '游客', '2019-03-10 19:18:34');

-- ----------------------------
-- Table structure for readenjoy_navigation
-- ----------------------------
DROP TABLE IF EXISTS `readenjoy_navigation`;
CREATE TABLE `readenjoy_navigation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `url` varchar(255) NOT NULL COMMENT '导航链入的页面',
  `status` tinyint(1) DEFAULT NULL COMMENT '类别状态1-正常,2-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `creat_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readenjoy_navigation
-- ----------------------------
INSERT INTO `readenjoy_navigation` VALUES ('100001', '0', '首页', 'index.html', '1', null, '2018-05-23 19:09:26', '2018-05-23 19:09:29');
INSERT INTO `readenjoy_navigation` VALUES ('100002', '0', '分类', 'book-category.html?categoryInfo=0-全部-0', '1', null, '2018-05-23 19:09:32', '2018-05-23 19:09:34');
INSERT INTO `readenjoy_navigation` VALUES ('100003', '0', '致谢作者', 'thx-writers.html', '1', null, '2018-05-23 19:09:53', '2018-05-23 19:09:57');

-- ----------------------------
-- Table structure for readenjoy_report
-- ----------------------------
DROP TABLE IF EXISTS `readenjoy_report`;
CREATE TABLE `readenjoy_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_version_id` int(11) DEFAULT NULL COMMENT '举报书籍版本',
  `report_reason` varchar(250) DEFAULT NULL COMMENT '举报原因',
  `report_name` varchar(50) DEFAULT NULL COMMENT '举报人',
  `upload_name` varchar(50) DEFAULT NULL COMMENT '书籍版本上传人',
  `report_status` int(11) DEFAULT NULL COMMENT '该举报信息的状态 --1 已处理 0 未处理',
  `report_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '举报时间',
  PRIMARY KEY (`id`),
  KEY `bookVersion` (`book_version_id`),
  KEY `reportName` (`report_name`),
  KEY `uploadName` (`upload_name`),
  CONSTRAINT `bookVersion` FOREIGN KEY (`book_version_id`) REFERENCES `readenjoy_bookversion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reportName` FOREIGN KEY (`report_name`) REFERENCES `readenjoy_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uploadName` FOREIGN KEY (`upload_name`) REFERENCES `readenjoy_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readenjoy_report
-- ----------------------------
INSERT INTO `readenjoy_report` VALUES ('10', '81', '携带病毒', 'HBHB', 'HBHB', '1', '2019-03-09 21:46:18');
INSERT INTO `readenjoy_report` VALUES ('12', '81', '携带病毒', 'HBHB', 'HBHB', '0', '2019-01-28 14:54:58');
INSERT INTO `readenjoy_report` VALUES ('13', '82', '包含恶意广告', 'HBHB', 'HBHB', '0', '2019-02-18 18:32:06');
INSERT INTO `readenjoy_report` VALUES ('14', '78', '充满了色情', 'HBHB', 'HBHB', '0', '2019-03-10 17:38:25');

-- ----------------------------
-- Table structure for readenjoy_user
-- ----------------------------
DROP TABLE IF EXISTS `readenjoy_user`;
CREATE TABLE `readenjoy_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `gender` varchar(10) DEFAULT NULL COMMENT '用户性别',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `email` varchar(50) DEFAULT NULL COMMENT '用户email',
  `phone` varchar(20) DEFAULT NULL COMMENT '用户电话',
  `headPic` varchar(200) DEFAULT NULL COMMENT '用户头像',
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `signs` varchar(250) DEFAULT NULL COMMENT '用户签名',
  `role` int(4) NOT NULL COMMENT '角色0-管理员,1-普通用户',
  `status` int(4) DEFAULT NULL COMMENT '用户使用状态 0-用户邮箱未验证 1-用户邮箱 2-用户被停用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readenjoy_user
-- ----------------------------
INSERT INTO `readenjoy_user` VALUES ('13', 'admin1', '男', 'EEA3DB55B70381B0EF69599A77150BEC', '44213403214@qq.com', null, 'default.jpg', '您母亲名字是？', 'HB', '我很爱看书', '0', '2', '2018-06-17 23:39:41', '2018-06-17 23:39:41');
INSERT INTO `readenjoy_user` VALUES ('14', '20152670603911', '男', 'EEA3DB55B70381B0EF69599A77150BEC', '442340111324@qq.com', null, 'default.jpg', '您母亲名字是？', '答案', '我很爱看书', '0', '2', '2018-06-17 23:40:48', '2018-06-17 23:40:48');
INSERT INTO `readenjoy_user` VALUES ('15', '2015267060113911', '男', 'EA180C1D1E1C05E20D43563405082B10', '44213401324@qq.com', null, 'default.jpg', '您母亲名字是？', '答案', '我很爱看书', '0', '1', '2018-06-17 23:41:56', '2019-01-20 17:23:39');
INSERT INTO `readenjoy_user` VALUES ('18', 'aqqiuuu', '女', '75D9ACA256BAA763E7F19C9D4EED3F64', '13177778@169.com', null, 'default.jpg', '您母亲的名字是？', 'tu', '世界和平', '0', '1', '2018-07-08 13:54:01', '2018-07-08 13:54:01');
INSERT INTO `readenjoy_user` VALUES ('43', 'HBHB', '女', 'EA180C1D1E1C05E20D43563405082B10', '442340324@qq.com', null, '5b0b2894-b770-4b48-8703-f1dc66124fdc.png', null, null, '嘻嘻嘻嘻', '1', '1', '2019-01-19 14:51:00', '2019-01-21 13:38:22');
INSERT INTO `readenjoy_user` VALUES ('44', '201526706039', null, 'EA180C1D1E1C05E20D43563405082B10', '44234032411@qq.com', null, 'default.jpg', null, null, null, '0', '0', '2019-01-19 14:56:17', '2019-01-19 14:56:17');
INSERT INTO `readenjoy_user` VALUES ('45', '201526706037', null, 'D846FF3FDEBE8A1FDE153C6D58616FEC', 'hbheyhoo@163.com', null, 'default.jpg', null, null, null, '1', '1', '2019-02-18 20:50:29', '2019-03-08 10:05:29');
INSERT INTO `readenjoy_user` VALUES ('48', '游客', '男', '000000', '000000', '000000', 'default.jpg', '000000', '000000', '000000', '0', '1', '2019-02-26 22:02:13', '2019-02-26 22:02:17');

-- ----------------------------
-- Table structure for readenjoy_usercollection
-- ----------------------------
DROP TABLE IF EXISTS `readenjoy_usercollection`;
CREATE TABLE `readenjoy_usercollection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL COMMENT '收藏者用户名',
  `book_version_ID` int(11) DEFAULT NULL COMMENT '收藏书籍的ID号',
  `collect_time` datetime DEFAULT NULL COMMENT '用户收藏时间',
  PRIMARY KEY (`id`),
  KEY `foreKey` (`user_name`),
  KEY `forekeybooversionid` (`book_version_ID`),
  CONSTRAINT `foreKey` FOREIGN KEY (`user_name`) REFERENCES `readenjoy_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `forekeybooversionid` FOREIGN KEY (`book_version_ID`) REFERENCES `readenjoy_bookversion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readenjoy_usercollection
-- ----------------------------
INSERT INTO `readenjoy_usercollection` VALUES ('104', 'HBHB', '77', '2019-01-27 15:57:31');
INSERT INTO `readenjoy_usercollection` VALUES ('105', 'HBHB', '81', '2019-01-27 17:26:49');
INSERT INTO `readenjoy_usercollection` VALUES ('111', 'HBHB', '80', '2019-01-28 14:02:08');
INSERT INTO `readenjoy_usercollection` VALUES ('112', 'HBHB', '79', '2019-01-28 14:03:45');
INSERT INTO `readenjoy_usercollection` VALUES ('113', 'HBHB', '82', '2019-02-18 16:59:40');
INSERT INTO `readenjoy_usercollection` VALUES ('114', 'HBHB', '86', '2019-04-10 16:20:52');
INSERT INTO `readenjoy_usercollection` VALUES ('115', 'HBHB', '85', '2019-04-13 21:30:49');

-- ----------------------------
-- Table structure for readenjoy_userdownload
-- ----------------------------
DROP TABLE IF EXISTS `readenjoy_userdownload`;
CREATE TABLE `readenjoy_userdownload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL COMMENT '收藏者用户名',
  `book_version_ID` int(11) DEFAULT NULL COMMENT '收藏书籍的ID号',
  `down_time` datetime DEFAULT NULL COMMENT '用户收藏时间',
  PRIMARY KEY (`id`),
  KEY `forekey2` (`user_name`),
  KEY `forekeybooversionid2` (`book_version_ID`),
  CONSTRAINT `forekey2` FOREIGN KEY (`user_name`) REFERENCES `readenjoy_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `forekeybooversionid2` FOREIGN KEY (`book_version_ID`) REFERENCES `readenjoy_bookversion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readenjoy_userdownload
-- ----------------------------
INSERT INTO `readenjoy_userdownload` VALUES ('25', 'HBHB', '77', '2019-01-27 15:57:41');
INSERT INTO `readenjoy_userdownload` VALUES ('26', 'HBHB', '80', '2019-02-18 18:20:28');
INSERT INTO `readenjoy_userdownload` VALUES ('27', '201526706037', '80', '2019-02-22 15:31:40');
INSERT INTO `readenjoy_userdownload` VALUES ('28', 'HBHB', '86', '2019-04-10 16:20:47');
INSERT INTO `readenjoy_userdownload` VALUES ('29', 'HBHB', '86', '2019-04-13 21:27:35');
INSERT INTO `readenjoy_userdownload` VALUES ('30', 'HBHB', '86', '2019-04-13 21:27:43');
INSERT INTO `readenjoy_userdownload` VALUES ('31', 'HBHB', '86', '2019-04-13 21:27:46');
INSERT INTO `readenjoy_userdownload` VALUES ('32', 'HBHB', '86', '2019-04-13 21:29:11');
INSERT INTO `readenjoy_userdownload` VALUES ('33', 'HBHB', '86', '2019-04-13 21:37:42');
INSERT INTO `readenjoy_userdownload` VALUES ('34', 'HBHB', '82', '2019-05-10 13:15:00');

-- ----------------------------
-- Table structure for readenjoy_userupload
-- ----------------------------
DROP TABLE IF EXISTS `readenjoy_userupload`;
CREATE TABLE `readenjoy_userupload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL COMMENT '上传者用户名',
  `book_version_ID` int(11) DEFAULT NULL COMMENT '上传书籍的ID号',
  PRIMARY KEY (`id`),
  KEY `forekey3` (`user_name`),
  KEY `forebookversionid3` (`book_version_ID`),
  CONSTRAINT `forebookversionid3` FOREIGN KEY (`book_version_ID`) REFERENCES `readenjoy_bookversion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `forekey3` FOREIGN KEY (`user_name`) REFERENCES `readenjoy_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readenjoy_userupload
-- ----------------------------
INSERT INTO `readenjoy_userupload` VALUES ('75', 'HBHB', '75');
INSERT INTO `readenjoy_userupload` VALUES ('76', 'HBHB', '76');
INSERT INTO `readenjoy_userupload` VALUES ('77', 'HBHB', '77');
INSERT INTO `readenjoy_userupload` VALUES ('78', 'HBHB', '78');
INSERT INTO `readenjoy_userupload` VALUES ('79', 'HBHB', '79');
INSERT INTO `readenjoy_userupload` VALUES ('80', 'HBHB', '80');
INSERT INTO `readenjoy_userupload` VALUES ('81', 'HBHB', '81');
INSERT INTO `readenjoy_userupload` VALUES ('82', 'HBHB', '82');
INSERT INTO `readenjoy_userupload` VALUES ('83', '201526706037', '83');
INSERT INTO `readenjoy_userupload` VALUES ('84', 'HBHB', '84');
INSERT INTO `readenjoy_userupload` VALUES ('85', 'HBHB', '85');
INSERT INTO `readenjoy_userupload` VALUES ('86', 'HBHB', '86');

-- ----------------------------
-- Table structure for readenjoy_writer
-- ----------------------------
DROP TABLE IF EXISTS `readenjoy_writer`;
CREATE TABLE `readenjoy_writer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '作家id',
  `writer_name` varchar(50) DEFAULT NULL COMMENT '作家名',
  `writer_birth` varchar(50) DEFAULT NULL COMMENT '作家出生-去世',
  `writer_info` varchar(500) DEFAULT NULL COMMENT '作家简介',
  `writer_image` varchar(200) DEFAULT NULL,
  `enter_time` varchar(20) DEFAULT NULL COMMENT '版权结束时期',
  `writer_from` varchar(50) DEFAULT NULL COMMENT '作家所属国籍',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readenjoy_writer
-- ----------------------------
INSERT INTO `readenjoy_writer` VALUES ('1', '罗广斌', '1924 ~ 1967', '罗广斌（1924－1967），作家。重庆忠县人。1948年加入中国共产党，国民党军第十六兵团司令官罗广文的胞弟，著名物理学家杨振宁的学生。1948年被捕，囚禁在重庆中美合作所渣滓洞、白公馆集中营。建国后，历任青年团重庆市委统战部部长、重庆市民主青年联盟副主席。后在重庆市文联专门从事创作。合著革命回忆录《在烈火中永生》、长篇小说《红岩》。文化大革命中受到迫害，跳楼自杀。', '罗广斌.jpg', '2018', '中国', '2019-03-03 16:39:06', '2019-03-03 16:39:06');
INSERT INTO `readenjoy_writer` VALUES ('2', '叶以群', '1911～ 1966', '叶以群(1911～1966)原名叶灿、叶华蒂，笔名以群，安徽歙县人。文艺理论家。曾留学日本，就读于东京法政大学经济系。1931年回国，参加中国左翼作家联盟，任组织部长，领导左联活动。1932年加入中国共产党，担任过《北斗》、《上海文学》、《收获》等杂志的主编或副主编。1932年开始发表作品。1954年加入中国作家协会。以群在文艺理论方面造诣极深，著述很多，主编有高校教材《文学的基本原理》；著作有《创作漫谈》、《文学的基础知识》、《在文艺思想战线上》、《鲁迅的文艺思想》等十多种。', '叶以群.jpg', '2017', '中国', '2019-03-03 18:22:54', '2019-03-03 18:22:54');
INSERT INTO `readenjoy_writer` VALUES ('3', '老舍', '1899 ～ 1966', '老舍（1899年2月3日—1966年8月24日），原名舒庆春，另有笔名絜青、鸿来、非我等，字舍予。因为老舍生于阴历立春，父母为他取名“庆春”，大概含有庆贺春来、前景美好之意。上学后，自己更名为舒舍予，含有“舍弃自我”，亦即“忘我”的意思。北京满族正红旗人。中国现代小说家、作家，语言大师、人民艺术家，新中国第一位获得“人民艺术家”称号的作家。代表作有《骆驼祥子》，《四世同堂》，剧本《茶馆》。\r\n老舍的一生，总是忘我地工作，他是文艺界当之无愧的“劳动模范”。', '老舍.jpg', '2017', '中国', '2019-03-03 18:22:54', '2019-03-03 18:22:54');
INSERT INTO `readenjoy_writer` VALUES ('4', '任铭善', '1912 ~ 1967', '任铭善（1912－1967），江苏如东人；1935年毕业于之江大学国文系；曾任之江大学讲师、浙江大学教授；建国后，历任浙江师范学院教授、副教务长，杭州大学教授，民进浙江省委第一届副主任委员；“反右”期间被打为“极右分子”，经历坎坷，“文化大革命”爆发后，任先生已患肝癌绝症；次年，终于郁郁以殁，享年仅54岁；任铭善先生长期从事古文献、古代汉语、现代汉语的研究和教学；著有《礼记目录后案》、《汉语语音史概要》等，合著《古汉通论》。', '任铭善.jpg', '2018', '中国', '2019-03-03 18:22:54', '2019-03-03 18:22:54');
INSERT INTO `readenjoy_writer` VALUES ('5', '李嘉言', '1911 ~ 1967', '李嘉言（1911~1967），字慎予，笔名高芒、景仰、家雁、贾彦、李常山、李慎予，河南武陟人。1958年秋受国务院古籍整理出版规划小组指派，作贾岛《长江集》校订工作，1964年完成。十年动乱中受迫害，1967年去世。生前任中国作家协会会员、河南省文联委员、省政协委员、《光明日报？文学遗产》编委。', '李嘉言.jpg', '2018', '中国', '2019-03-03 18:22:54', '2019-03-03 18:22:54');
INSERT INTO `readenjoy_writer` VALUES ('6', '周作人', '1885 ~ 1967', '周作人（1885~1967）原名櫆寿（后改为奎绶），字星杓，又名启明、启孟、起孟，笔名遐寿、仲密、岂明，号知堂、药堂、独应等，浙江绍兴人。是鲁迅（周树人）之弟，周建人之兄。中国现代著名散文家、文学理论家、评论家、诗人、翻译家、思想家，中国民俗学开拓人，新文化运动的杰出代表。\r\n		历任国立北京大学教授、东方文学系主任，燕京大学新文学系主任、客座教授。新文化运动中是《新青年》的重要同人作者，并曾任“新潮社”主任编辑。“五四运动”之后，与郑振铎、沈雁冰、叶绍钧、许地山等人发起成立“文学研究会”。', '周作人.jpg', '2018', '中国', '2019-03-03 18:22:54', '2019-03-03 18:22:54');
INSERT INTO `readenjoy_writer` VALUES ('7', '伊利亚·爱伦堡', '1891 ~ 1967', '伊利亚·爱伦堡（1891—1967），全名伊里亚·格里戈里耶维奇·爱伦堡，苏联犹太人作家。\r\n1891年1月14日，爱伦堡出生在沙俄统治下的乌克兰基辅。青年时参加布尔什维克革命，在流亡巴黎期间开始文学生涯。曾长期作为记者派驻国外，卫国战争中发表了不少反法西斯的政论。曾先后两次获得斯大林奖金。 [1] \r\n1954年发表中篇小说《解冻》，开创了解冻文学的潮流。1960年—1964年发表《人·岁月·生活》，是最早公开批评斯大林的作品，被誉为苏联 “解冻文学”的开山巨作和“欧洲的文艺史诗”。', '爱伦堡.jpg', '2018', '苏联', '2019-03-03 16:46:43', '2019-03-03 16:46:43');
INSERT INTO `readenjoy_writer` VALUES ('8', '卡尔·桑德堡（Carl Sandburg）', '1878 ~ 1967', '卡尔·桑德堡，男，美国著名诗人、传记作者和新闻记者，代表作有《亚伯拉罕：战争的年代》、《太阳灼伤的西方石板》、《蜜与盐》等。善于运用通俗语言和平常讲话时的节奏描绘先驱开拓的日子里的赤裸而又强有力的现实主义以及美国工业化扩张，表达中西部的乐观和民主精神，被誉为“人民的诗人”。1967年逝世时，时任美国总统的约翰逊评价道，“卡尔·桑德堡不仅仅是美国之声，也不仅仅是一个有力量和天赋的诗人，他就是美国。”目前《林肯传》以及大量诗歌（如《雾》）已在国内出版。', '卡尔·桑德堡.jpg', '2018', '美国', '2019-03-03 16:50:00', '2019-03-03 16:50:00');
INSERT INTO `readenjoy_writer` VALUES ('9', '兰斯顿·休斯（Langston Hughes）', '1902 ~ 1967', '兰斯顿·休斯（Langston Hughes，1902.02.01—1967.05.22）是二十世纪美国最杰出的黑人作家之一，也是美国黑人文艺复兴（“哈莱姆”文艺复兴）运动中涌现出来的著名黑人诗人。他最引人注目的成就在于将爵士乐与黑人民歌引入诗歌，创作出独具特色的“美国黑人诗歌”。代表作包括《犹太人的好衣服》（1927）、《梦乡人》（1932）、《哈莱姆的莎士比亚》（1942）等，长篇小说《不是没有笑的》。成名作《黑人谈河流》饱受盛名。目前国内已出版《休斯诗选》及大量短篇小说。', '兰斯顿·休斯.jpg', '2018', null, '2019-03-03 16:52:42', '2019-03-03 16:52:42');
INSERT INTO `readenjoy_writer` VALUES ('10', '玛格丽特·肯尼迪（Margaret Kennedy）', '1896 ~1967', '玛格丽特·肯尼迪（Margaret Kennedy, 1896-04-23 至 1967-07-31）英国小说家、剧作家。她的小说《永恒的少女》获得巨大成功，并被改编为电影，由奥斯卡最佳女演员琼·芳登出演。', '玛格丽特·肯尼迪.jpg', '2018', null, '2019-03-03 16:55:06', '2019-03-03 16:55:06');
INSERT INTO `readenjoy_writer` VALUES ('11', '伊萨克·多伊彻(\r\nIsaac Deutcher)', '1906 ~1967', '伊萨克·多伊彻，1906年生于波兰，20岁加入波兰共产党，很快成为著名的波兰思想家、苏联及联共（布）问题专家。1932年加入托洛茨基反对派，1939年侨居伦敦。一生著述甚丰，其中最有影响的是《先知三部曲》及《斯大林政治评传》。1967年逝世。\r\n　　他的《先知三部曲》迄今仍是举世公认的研究托洛茨基的最权威著作，已被译成多种文字。', '伊萨克·多伊彻.jpg', '2018', null, '2019-03-03 16:59:33', '2019-03-03 16:59:33');
INSERT INTO `readenjoy_writer` VALUES ('12', '亚瑟·兰塞姆（Arthur Ransome）', '1884 ~ 1967', '亚瑟·兰塞姆（Arthur Ransome， 1924 - 1967）英国作家、记者。他的儿童文学《燕子号与亚马逊号》系列，包含关于乡村生活和英国航行的12个故事。该系列故事曾被评为英国百年经典小说，现已有中文译文，如《燕子号与亚马逊号》《向北极进发》《布尔河畔的黑鸭子》《雾海迷航》《蟹岛夺宝》等，表现了孩子们在假日中的小小冒险。故事主要发生于坎布里亚郡的湖区，成为当地旅游的重要背景故事。', '亚瑟·兰塞姆.jpg', '2018', null, '2019-03-03 17:02:01', '2019-03-03 17:02:01');
INSERT INTO `readenjoy_writer` VALUES ('13', '帕特里克·卡瓦纳（Patrick Kavanagh）', '1905 ~ 1967', '帕特里克·卡瓦纳( Patrick Kavanagh，1905-1967)，爱尔兰诗人。 1905年10月21日生于爱尔兰的莫纳亨郡。在家乡未读完小学就辍学跟随父亲务农并干皮匠活，其间长达16年之久。此间踏上自学成才之路。小说《泰瑞福林》及诗歌《大饥荒》完美刻画了爱尔兰当地的日常生活，后在都柏林大学设立“帕特里克·卡瓦纳档案馆”。', '帕特里克·卡瓦纳.jpg', '2018', null, '2019-03-03 17:04:11', '2019-03-03 17:04:11');
INSERT INTO `readenjoy_writer` VALUES ('14', '赵少咸', '1884～1966', '赵少咸：语言文字学家。名世忠，字少咸，原籍安徽省休宁县，生于四川省成都市。赵振铎祖父。他师承戴震、段玉裁、王氏父子，且与章太炎、黄侃等有交往，平生致力于汉语语音词义之学。主要著作有《〈广韵〉疏证》、《〈经典释文〉集说附笺》、《古今切语表》、《诗韵谱》等。', '赵少咸.jpg', '2017', '中国', '2019-03-03 18:23:14', '2019-03-03 18:23:14');
INSERT INTO `readenjoy_writer` VALUES ('15', '陈笑雨', '1917 ~ 1966', '陈笑雨（1917—1966年），杂文家、文艺评论家。原名陈荫恩，笔名司马龙、马铁丁。靖江新港镇人。陈笑雨的著作有《思想杂谈》、《马铁丁杂文集（第一本）》、《复青年读者》、《杂文集（第二本）》、《杂文集（第三本）》、《杂文集（第四本）》、《共产主义道德是最高尚的道德》、《思想杂谈选集》、《杂文杂诗集》、《说东道西集》等等。', '陈笑雨.jpg', '2017', '中国', '2019-03-03 18:23:15', '2019-03-03 18:23:15');
INSERT INTO `readenjoy_writer` VALUES ('16', '邓拓', '1912 ~ 1966', '邓拓（1912—1966），原名邓子健，笔名叫马南邨、邓云特，福建闽侯人，无产阶级革命战士，当代杰出的新闻工作者、政论家、历史学家、诗人和杂文家，他还是一位书画收藏家。', '邓拓.jpg', '2017', '中国', '2019-03-03 18:23:15', '2019-03-03 18:23:15');
INSERT INTO `readenjoy_writer` VALUES ('19', '傅雷', '1908 ~ 1966', '傅雷（1908年4月7日－1966年9月3日），字怒安，号怒庵，生于原江苏省南汇县下沙乡（今上海市浦东新区航头镇），中国著名的翻译家、作家、教育家、美术评论家，中国民主促进会（民进）的重要缔造者之一。 [1]  早年留学法国巴黎大学。他翻译了大量的法文作品，其中包括巴尔扎克、罗曼·罗兰、伏尔泰等名家著作。20世纪60年代初，傅雷因在翻译巴尔扎克作品方面的卓越贡献，被法国巴尔扎克研究会吸收为会员。他的全部译作，现经家属编定，交由安徽人民出版社编成《傅雷译文集》，从1981年起分15卷出版。', '傅雷.jpg', '2017', '中国', '2019-03-03 18:23:15', '2019-03-03 18:23:15');
INSERT INTO `readenjoy_writer` VALUES ('20', '陈梦家', '1911 ~ 1966', '陈梦家（1911～1966），浙江上虞人，生于南京。曾用笔名陈慢哉，中国现代著名古文字学家、考古学家、诗人。陈梦家在三十年代的诗名很大，曾与闻一多、徐志摩、朱湘一起被目为“新月诗派的四大诗人”。他16岁开始写诗。其诗先学徐志摩，后学闻一多。1929年10月，在《新月》杂志发表处女作新诗《那一晚》，引起诗坛瞩目。后又以“陈漫哉”为笔名发表大量新诗。1931年1月，编成《梦家诗集》，由新月书店出版。同年9月，又编成《新月诗选》出版。其诗重视表现“自我”，注重音韵和谐及整体匀称，善于吸收格律诗特点写自由诗，对新月派的形成和发展影响较大。', '陈梦家.jpg', '2017', '中国', '2019-03-03 18:23:15', '2019-03-03 18:23:15');
INSERT INTO `readenjoy_writer` VALUES ('21', '黄文弼', '1893 ~ 1966', '黄文弼（1893.4—1966.12），男，字仲良，湖北汉川人，中国科学院考古研究所研究员，考古学家，西北史地学家。1918年毕业于北京大学哲学系。1919年到北京大学研究所国学门任教，并于1927～1930年参加中瑞西北科学考察团的内蒙古、新疆考察活动。1934～1937年任西北科学考察团专任研究员，1935年又以中央古物保管委员会委员身份派驻西安任办事处主任，进行整理碑林等工作。抗日战争期间任西北联合大学和四川大学教授，又任西北大学历史、边政两系主任。1966年12月18日在北京病逝。', '黄文弼.jpg', '2017', '中国', '2019-03-03 18:23:15', '2019-03-03 18:23:15');
INSERT INTO `readenjoy_writer` VALUES ('22', '向达', '1900 ~ 1966', '向达（1900－1966），湖南溆浦人。字觉明，笔名方回、佛陀耶舍，土家族。1919年考入南京高等师范学校；1924年后任商务印书馆编译员、北平图书馆编纂委员会委员兼北京大学讲师；1935年秋到牛津大学鲍德利（Bodley）图书馆工作；在英国博物馆检索敦煌写卷和汉文典籍；1937年赴德国考察劫自中国的壁画写卷；1938年回国后任浙江大学、西南联合大学教授；抗战胜利后，任北京大学历史系教授兼掌北大图书馆；新中国成立后，任北京大学历史系教授、图书馆馆长，中国科学院哲学社会科学部委员；“文革”爆发后惨遭批斗折磨，不幸辞世。', '向达.jpg', '2017', '中国', '2019-03-03 18:23:15', '2019-03-03 18:23:15');
INSERT INTO `readenjoy_writer` VALUES ('23', '李平心', '1907 ~ 1966', '李平心（1907-1966），江西南昌人，原名循钺，又名圣悦，笔名李鼎声、邵翰齐等。华东师范大学历史系教授，现代著名历史学家、社会学家，中共党员，中国民主促进会会员。', '李平心.jpg', '2017', '中国', '2019-03-03 18:23:15', '2019-03-03 18:23:15');
INSERT INTO `readenjoy_writer` VALUES ('24', '陈恭禄', '1900 ~ 1966', '陈恭禄，江苏省丹徒县高资镇人，早年入扬州美汉中学、南京金陵大学历史系毕业，学习期间，即编写出版《日本全史》。\r\n民国15年毕业后，任南京明德中学教师，写成《印度通史》。\r\n民国17年9月，至金陵大学讲授中国近百年史；\r\n民国22年夏，任武汉大学教授，出版《中国近代史》，数月内售至四版，被列为当时大学丛书之一，其后又重印再版多次，在当时的学界影响极大，历三年仍回金陵大学，出版《中国近百年史》。发表了《秦始皇与儒家思想》、《汉代文化统一论》、《三国时蜀户口之估计》等论文。', '陈恭禄.jpg', '2017', '中国', '2019-03-03 18:23:15', '2019-03-03 18:23:15');
INSERT INTO `readenjoy_writer` VALUES ('25', '汪篯', '1916 ~ 1966', '汪篯（1916-1966），江苏江都人。中国当代历史学家，师从陈寅恪，北大历史系教授，文革初去世。毕生从事隋唐史研究，身后文稿札记大部分佚失。所余论作由唐长孺、吴宗国、梁太济、宋家钰、席康元等学者编选为此书，计二十二篇。', '汪篯.jpg', '2017', '中国', '2019-03-03 18:23:15', '2019-03-03 18:23:15');
INSERT INTO `readenjoy_writer` VALUES ('26', '孔厥', '1914 ~ 1966', '孔厥（1914--1966），原名郑志万，字云鹏，改名郑挚，笔名沈毅、孔厥。江苏吴县（今苏州）人，作家。著有长篇小说《新儿女英雄传》（与袁静合作）、《新儿女英雄续传》，短篇小说集《受苦人》，《生死缘》，另有《中朝儿女》、《白洋淀水战》、《血尸案》等。', '孔厥.jpg', '2017', '中国', '2019-03-03 18:23:15', '2019-03-03 18:23:15');
