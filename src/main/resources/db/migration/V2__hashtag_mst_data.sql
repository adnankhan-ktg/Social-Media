CREATE TABLE hashtag_mst (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    categories_id INT,
    FOREIGN KEY (categories_id) REFERENCES category_mst(id)
);

INSERT INTO hashtag_mst (name, created_at, categories_id)
VALUES
  ('#SilverScreenStories', NOW(), 1),
  ('#PopCulturePulse', NOW(), 1),
  ('#EntertainmentEscapade', NOW(), 1),
  ('#FamousFacesFriday', NOW(), 1),
  ('#StageAndScreenSensation', NOW(), 1),
  ('#EntertainmentInsiders', NOW(), 1),
  ('#GlamorousGossip', NOW(), 1),
  ('#BlockbusterBuzz', NOW(), 1),
  ('#CelebStyleChronicles', NOW(), 1),
  ('#MusicMantra', NOW(), 1),


  ('#LifeUnscripted', NOW(), 2),
  ('#WellnessWanderlust', NOW(), 2),
  ('#TrendyTraditions', NOW(), 2),
  ('#AdventureSeeker', NOW(), 2),
  ('#LuxeLifeJourney', NOW(), 2),
  ('#EatDrinkDiscover', NOW(), 2),
  ('#StyleTherapy', NOW(), 2),
  ('#LifeElevated', NOW(), 2),
  ('#FamilyAndFridays', NOW(), 2),
  ('#MindfulMoments', NOW(), 2),


  ('#InspireTheSoul', NOW(), 3),
  ('#MotivationNation', NOW(), 3),
  ('#DreamsToReality', NOW(), 3),
  ('#PositivePathways', NOW(), 3),
  ('#InspirationalImpact', NOW(), 3),
  ('#InspireDailyDose', NOW(), 3),
  ('#EmpowermentEveryday', NOW(), 3),
  ('#AspireToInspire', NOW(), 3),
  ('#StrengthInStories', NOW(), 3),
  ('#UnstoppableDreams', NOW(), 3),


  ('#EpicureanEats', NOW(), 4),
  ('#CulinaryCreations', NOW(), 4),
  ('#FoodieFiesta', NOW(), 4),
  ('#DeliciousDishes', NOW(), 4),
  ('#GourmetGoals', NOW(), 4),
  ('#CookingChronicles', NOW(), 4),
  ('#PlatingPerfection', NOW(), 4),
  ('#FoodieAdventures', NOW(), 4),
  ('#FlavorFusion', NOW(), 4),
  ('#SavorTheMoment', NOW(), 4),


  ('#WanderlustJourney', NOW(), 5),
  ('#AdventurousExplorer', NOW(), 5),
  ('#TravelGoalsAchieved', NOW(), 5),
  ('#ExploreTheUnknown', NOW(), 5),
  ('#RoamWithPurpose', NOW(), 5),
  ('#AdventureTime', NOW(), 5),
  ('#DiscoverNewHorizons', NOW(), 5),
  ('#TravelBeyondBorders', NOW(), 5),
  ('#WanderWisely', NOW(), 5),
  ('#JourneyOfALifetime', NOW(), 5),


  ('#ChicAndUnique', NOW(), 6),
  ('#StyleSensation', NOW(), 6),
  ('#RunwayReady', NOW(), 6),
  ('#FashionForwardFridays', NOW(), 6),
  ('#DapperDaily', NOW(), 6),
  ('#TrendsettersClub', NOW(), 6),
  ('#CoutureCraze', NOW(), 6),
  ('#StyleIcons', NOW(), 6),
  ('#GlamourGurus', NOW(), 6),
  ('#FashionistaFaves', NOW(), 6),


  ('#TechTrendsToday', NOW(), 7),
  ('#GadgetGalore', NOW(), 7),
  ('#InnovateWithTech', NOW(), 7),
  ('#DigitalDiscovery', NOW(), 7),
  ('#TechTalkTuesday', NOW(), 7),
  ('#FutureTechNow', NOW(), 7),
  ('#GeekyGoodness', NOW(), 7),
  ('#DigitalInnovators', NOW(), 7),
  ('#TechLifeHacks', NOW(), 7),
  ('#InnovationInsights', NOW(), 7),


  ('#LearnAndLead', NOW(), 8),
  ('#KnowledgeNinja', NOW(), 8),
  ('#StudySquadGoals', NOW(), 8),
  ('#EducationEmpowers', NOW(), 8),
  ('#StudentSuccessStory', NOW(), 8),
  ('#BookwormBrigade', NOW(), 8),
  ('#MentorshipMatters', NOW(), 8),
  ('#AcademicAchievers', NOW(), 8),
  ('#LearnTogether', NOW(), 8),
  ('#SmartAndStudious', NOW(), 8),


  ('#PetParadise', NOW(), 9),
  ('#AnimalAntics', NOW(), 9),
  ('#FurFamilyFun', NOW(), 9),
  ('#AdorableAdventures', NOW(), 9),
  ('#PetsofIG', NOW(), 9),
  ('#PawsomeMoments', NOW(), 9),
  ('#WildlifeWonders', NOW(), 9),
  ('#FurryFriendsForever', NOW(), 9),
  ('#PetLoveInPictures', NOW(), 9),
  ('#PetAdoptionStories', NOW(), 9),


  ('#WellnessWarrior', NOW(), 10),
  ('#HealthyHabitHeroes', NOW(), 10),
  ('#FitnessJourneyJoy', NOW(), 10),
  ('#MindBodyBalance', NOW(), 10),
  ('#WellnessWednesday', NOW(), 10),
  ('#NutritionNirvana', NOW(), 10),
  ('#MentalHealthMatters', NOW(), 10),
  ('#HealthyLivingHacks', NOW(), 10),
  ('#WellnessWins', NOW(), 10),


  ('#DreamHome', NOW(), 10),
  ('#HomeSweetDecor', NOW(), 11),
  ('#InteriorInspo', NOW(), 11),
  ('#DecorDreams', NOW(), 11),
  ('#HomeStyleHaven', NOW(), 11),
  ('#CozySpaces', NOW(), 11),
  ('#DesignDazzle', NOW(), 11),
  ('#DecorDelights', NOW(), 11),
  ('#HouseGoals', NOW(), 11),
  ('#InteriorDesignInspiration', NOW(), 11),
  ('#LivingInStyle', NOW(), 11),


  ('#LaughOutLoudComedy', NOW(), 12),
  ('#ComedyKings', NOW(), 12),
  ('#FunnyBoneTickler', NOW(), 12),
  ('#HilariousHumor', NOW(), 12),
  ('#StandUpLaughs', NOW(), 12),
  ('#ComedyCentral', NOW(), 12),
  ('#SmileWide', NOW(), 12),
  ('#HumorHeals', NOW(), 12),
  ('#LaughMakers', NOW(), 12),
  ('#ComedyGold', NOW(), 12),


  ('#MusicalMagic', NOW(), 13),
  ('#TuneInTuesdays', NOW(), 13),
  ('#SoundOfMusic', NOW(), 13),
  ('#MusicIsLife', NOW(), 13),
  ('#LiveMusicLove', NOW(), 13),
  ('#MelodicMoods', NOW(), 13),
  ('#MusicalInspiration', NOW(), 13),
  ('#JamSession', NOW(), 13),
  ('#MusicMania', NOW(), 13),
  ('#SongOfTheDay', NOW(), 13),


  ('#GlamGoals', NOW(), 14),
  ('#MakeupMagic', NOW(), 14),
  ('#BeautyByDesign', NOW(), 14),
  ('#GorgeousGlow', NOW(), 14),
  ('#MUAOnFleek', NOW(), 14),
  ('#BeautyInsider', NOW(), 14),
  ('#FlawlessFaces', NOW(), 14),
  ('#BeautyBlogger', NOW(), 14),
  ('#MakeupMastery', NOW(), 14),
  ('#GlamourGoddess', NOW(), 14),


  ('#GamingWorld', NOW(), 15),
  ('#GameOnGamer', NOW(), 15),
  ('#GamerLife', NOW(), 15),
  ('#GameTimeFun', NOW(), 15),
  ('#EsportsElite', NOW(), 15),
  ('#ControllerChampion', NOW(), 15),
  ('#GamingCommunity', NOW(), 15),
  ('#GameNights', NOW(), 15),
  ('#GameOnPoint', NOW(), 15),
  ('#LevelUpLife', NOW(), 15),


  ('#ArtisticAdventures', NOW(), 16),
  ('#CreateAndInspire', NOW(), 16),
  ('#CreativeExpressions', NOW(), 16),
  ('#ArtistsUnite', NOW(), 16),
  ('#InnovationInArt', NOW(), 16),
  ('#VisualVoyages', NOW(), 16),
  ('#ArtisticJourney', NOW(), 16),
  ('#CreateEveryday', NOW(), 16),
  ('#MasterpieceMoments', NOW(), 16),
  ('#ColorfulCanvas', NOW(), 16),


  ('#FitFam', NOW(), 17),
  ('#SportsMania', NOW(), 17),
  ('#FitnessGoals', NOW(), 17),
  ('#AthleteLife', NOW(), 17),
  ('#WorkoutWarrior', NOW(), 17),
  ('#ActiveLifestyle', NOW(), 17),
  ('#FitnessFriday', NOW(), 17),
  ('#SportsHeroes', NOW(), 17),
  ('#StrengthTraining', NOW(), 17),
  ('#HealthyHabits', NOW(), 17),


  ('#FamilyFirst', NOW(), 18),
  ('#ParentingPride', NOW(), 18),
  ('#KidsCorner', NOW(), 18),
  ('#ParentingAdventures', NOW(), 18),
  ('#FamilyLove', NOW(), 18),
  ('#FamilyFunTime', NOW(), 18),
  ('#MomLife', NOW(), 18),
  ('#DadLife', NOW(), 18),
  ('#ParentingJourney', NOW(), 18),
  ('#FamilyAdventures', NOW(), 18),


  ('#NatureNurtures', NOW(), 19),
  ('#EcoExplorer', NOW(), 19),
  ('#OutdoorEscapes', NOW(), 19),
  ('#GreenLiving', NOW(), 19),
  ('#NatureWonders', NOW(), 19),
  ('#WildlifeWatch', NOW(), 19),
  ('#ScenicViews', NOW(), 19),
  ('#ExploreNature', NOW(), 19),
  ('#NaturalBeauty', NOW(), 19),
  ('#EarthGuardians', NOW(), 19),


  ('#TechTrends', NOW(), 20),
  ('#ScienceFacts', NOW(), 20),
  ('#DiscoverScience', NOW(), 20),
  ('#TechInnovation', NOW(), 20),
  ('#TechWorld', NOW(), 20),
  ('#ScienceSundays', NOW(), 20),
  ('#TechTales', NOW(), 20),
  ('#FutureTech', NOW(), 20),
  ('#GeekChic', NOW(), 20);





