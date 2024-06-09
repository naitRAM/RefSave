package dev.ramimans.refsave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.ramimans.refsave.dao.ReferenceDaoImpl;


@SpringBootApplication
public class RefsaveApplication { // implements CommandLineRunner {

	@Autowired
	ReferenceDaoImpl dao;
	public static void main(String[] args) {
		SpringApplication.run(RefsaveApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {

	// 	Book book = new Book(UUID.randomUUID().toString(), LocalDateTime.now(), "Song of Solomon ref", "Song of Solomon", "This is some weird shit", 114);
	// 	dao.createRef("ami", book);

	// 	Tv tv = new Tv(UUID.randomUUID().toString(), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "Game of Thrones reference", "Game of Thrones S02E03", "Red wedding horror", LocalTime.of(1,5,43));
	// 	tv = dao.createRef("Majid", tv);
	// 	System.out.println(tv);
	// 	tv.setLabel("A Red Wedding Reference");
	// 	tv.setTimepoint(LocalTime.of(0, 59, 07));
	// 	dao.updateRef("Majid", tv);
	// 	System.out.println(dao.readReference("Majid", tv.getId()));
	// 	//dao.deleteRef("Majid", tv);
	// 	List<Reference> refs = dao.readReferences("Majid");
	// 	for (Reference ref : refs) {
	// 	System.out.println(ref);
	// 	System.out.println(ref.getClass().getName());
	// 	}
		
	// }

}
