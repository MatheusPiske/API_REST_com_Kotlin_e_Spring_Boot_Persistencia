package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Component

// É uma classe de serviço, porém, utilizamos o component por não estar especificamente atrelada aos services
@Component
class TopicoViewMapper: Mapper<Topico,TopicoView> {
    override fun map(t: Topico): TopicoView {
        return TopicoView (
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            status = t.status,
            dataCriacao = t.dataCriacao
        )
    }

}