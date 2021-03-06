//
//  ListCell.swift
//  McKinseyHACKS
//
//  Created by Jason Piao on 2016-10-22.
//  Copyright © 2016 Jason Piao. All rights reserved.
//

import UIKit

class ListCell: UITableViewCell {
    
    @IBOutlet weak var position: UILabel!
    @IBOutlet weak var company: UILabel!
    @IBOutlet weak var desc: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        
    }
    
    //Putting data into the cell
    func configureCell(job: NewJob) {
        position.text = job.jobTi
        company.text = job.comp
        desc.text = job.snip
    }

}
