package jm_social_project.profile_service;

import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.model.VisitedProfiles;
import jm_social_project.profile_service.repository.ProfileRepository;
import jm_social_project.profile_service.repository.VisitedProfileRepository;
import jm_social_project.profile_service.service.ProfileService;
import jm_social_project.profile_service.service.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@SpringBootApplication
public class ProfileServiceApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository repository;

	@Autowired
	private VisitedProfileRepository visitedProfileRepository;

	@Autowired
	private ProfileService service;

	@Value("${nearbyProfileSearchRadiusDefault}")
	private int nearbyProfileSearchRadiusDefault; // радиус поиска ближайших профилей, м
	@Value("${nearbyRandomProfileSearchRadius}")
	private int nearbyRandomProfileDefault; // радиус поиска cлучайного профиля, если диапазон юзером НЕ выбран, то радиус по дефолту 3000, м

	public static void main(String[] args) {
		SpringApplication.run(ProfileServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();
		visitedProfileRepository.deleteAll();

		//visitors
		Set<VisitedProfiles> visitedProfilesSet = new LinkedHashSet<>();
		visitedProfilesSet.add(new VisitedProfiles("1","2",new Date()));
		visitedProfilesSet.add(new VisitedProfiles("2","4",new Date()));
		visitedProfileRepository.saveAll(visitedProfilesSet);

		// save a couple of customers
		repository.save(new Profile("1", "1", false, "Adam", "Smith", "free", "https://", new Date(89, 2, 21), "cool boy", 45.032689,38.984449,new LinkedHashSet<VisitedProfiles>()));
		repository.save(new Profile("2", "2",false, "Adam1", "Smith1", "free", "https://", new Date(89, 2, 21),"cool boy", 45.032650,38.984756,new LinkedHashSet<VisitedProfiles>()));
		repository.save(new Profile("3", "3",false, "Adam2", "Smith2", "free", "https://", new Date(89, 2, 21),"cool boy", 45.032802,38.983685,new LinkedHashSet<VisitedProfiles>()));
		repository.save(new Profile("4", "4",false, "Adam3", "Smith3", "free", "https://", new Date(89, 2, 21),"cool boy", 45.032096,38.984231,new LinkedHashSet<VisitedProfiles>()));
		repository.save(new Profile("5", "5",false, "Adam4", "Smith4", "free", "https://", new Date(89, 2, 21),"cool boy", 45.033674,38.984821,new LinkedHashSet<VisitedProfiles>()));
		repository.save(new Profile("6", "6",false, "Adam5", "Smith5", "free", "https://", new Date(89, 2, 21),"cool boy", 45.085804,39.040221,new LinkedHashSet<VisitedProfiles>()));
		repository.save(new Profile("7", "7",false, "Adam6", "Smith6", "free", "https://", new Date(89, 2, 21),"cool boy", 45.032127,39.086379,visitedProfilesSet));

		// вывод всех юзеров в консоль
		System.out.println("Profiles found with findAll():");
		System.out.println("-------------------------------");
		for (Profile profile : repository.findAll()) {
			System.out.println(profile);
		}
		System.out.println();

		// вывод юзеров в радиусе 1000 метров
		System.out.println("Profiles in 1000m range:");
		System.out.println("--------------------------------");
		System.out.println(service.getNearbyProfiles(new Profile("1", "1",false, "Adam", "Smith", "free", "https://", new Date(89, 2, 21),"cool boy", 45.032680,38.984446,new LinkedHashSet<VisitedProfiles>()), nearbyProfileSearchRadiusDefault ).toString());
		System.out.println();

		// вывод расстояний от меня до юзеров
		System.out.println("profile distance between you and others:");
		System.out.println("--------------------------------");
		System.out.println(service.distanceBetweenProfiles(new Profile("1", "1",false, "Adam", "Smith", "free", "https://", new Date(89, 2, 21),"cool boy", 45.032680,38.984446,new LinkedHashSet<VisitedProfiles>()), nearbyProfileSearchRadiusDefault).toString());
		System.out.println();
	}
}